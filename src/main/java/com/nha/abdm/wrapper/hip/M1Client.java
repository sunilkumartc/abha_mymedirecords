/* (C) 2024 */
package com.nha.abdm.wrapper.hip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nha.abdm.wrapper.common.RequestManager;
import com.nha.abdm.wrapper.common.models.AuthData;
import com.nha.abdm.wrapper.common.models.AuthMethods;
import com.nha.abdm.wrapper.common.models.Consent1;
import com.nha.abdm.wrapper.common.models.Otp;
import com.nha.abdm.wrapper.common.models.RegistrationRequest;
import com.nha.abdm.wrapper.common.responses.AdhaarResponse;
import com.nha.abdm.wrapper.common.responses.ErrorResponse;
import com.nha.abdm.wrapper.common.responses.ErrorResponseWrapper;
import com.nha.abdm.wrapper.common.responses.FacadeResponse;
import com.nha.abdm.wrapper.common.responses.GenericResponse;
import com.nha.abdm.wrapper.hip.hrp.database.mongo.repositories.LogsRepo;
import com.nha.abdm.wrapper.hip.hrp.database.mongo.services.RequestLogService;
import com.nha.abdm.wrapper.hip.hrp.link.hipInitiated.requests.LinkRecordsRequest;
import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.Exceptions;

@Component
public class M1Client {

  @Value("${enrollABHA}")
  private String enrollABHA;

  @Value("${sandboxBaseUrl1}")
  private String sandboxBaseUrl1;
  @Value("${verifyOtpPath1}")
  private String verifyOtpPath1;

  @Autowired LogsRepo logsRepo;
  private final RequestManager requestManager;
  
  @Autowired RequestLogService requestLogService;
  
  @Autowired RSAEncryption rsaEncryption;

  @Autowired
  public M1Client(RequestManager requestManager) {
    this.requestManager = requestManager;
  }

  private static final Logger log = LogManager.getLogger(M1Client.class);

  /**
   * <B>hipInitiatedLinking</B>
   *
   * <p>1)Build the required body for /auth/init including abhaAddress.<br>
   * 2)Stores the request of linkRecordsRequest into requestLog.<br>
   * 3)makes a POST request to /auth/init API
   *
   * @param linkRecordsRequest Response which has authMode, patient details and careContexts.
   * @return it returns the requestId and status of initiation to the Facility for future tracking
   */
  public AdhaarResponse enrollABHA(String adhaarNumber) {

  

    log.debug("enrollABHA : " + adhaarNumber.toString());
    

    try {
        String encryptedAadhaarNumber = rsaEncryption.encrypt(adhaarNumber);

        String requestBody = "{\"aadhaar\": \"" + encryptedAadhaarNumber + "\"}";

      ResponseEntity<String> response =
          requestManager.fetchResponseFromGateway1("/api/v2/registration/aadhaar/generateOtp", requestBody);
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(response.getBody());

      // Extract specific fields
      String txnId = rootNode.path("txnId").asText();
      String mobileNumber = rootNode.path("mobileNumber").asText();
      log.debug(enrollABHA + " : enrollABHA: " +response.getBody());
      if (response.getStatusCode() == HttpStatus.ACCEPTED) {
          log.debug(enrollABHA + " : enrollABHA: " + response.getBody());
      } 
      return AdhaarResponse.builder()
          .txnId(txnId)
          .httpStatusCode(response.getStatusCode())
          .mobileNumber(mobileNumber)
          .build();
    } catch (Exception ex) {
        log.debug("enrollABHA : " + ex.getMessage());
        return null;
    }
    }
  
  public String verifyOTP(String otp) {

	  

	    log.debug("enrollotp : " + otp.toString());
	    

	    try {
	        String encryptedotpNumber = rsaEncryption.encrypt(otp);

	        String requestBody = "{\"otp\": \"" + encryptedotpNumber + "\"}";

	      ResponseEntity<String> response =
	          requestManager.fetchResponseFromGateway1("/api/v2/registration/aadhaar/verifyMobileOTP", requestBody);
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      JsonNode rootNode = objectMapper.readTree(response.getBody());

	      // Extract specific field
	      log.debug(enrollABHA + " : enrollABHA: " +response.getBody());
	      if (response.getStatusCode() == HttpStatus.ACCEPTED) {
	          log.debug(enrollABHA + " : enrollABHA: " + response.getBody());
	      } 
	      return response.getBody();
	    } catch (Exception ex) {
	        log.debug("enrollABHA : " + ex.getMessage());
	        return null;
	    }
	    }
}
