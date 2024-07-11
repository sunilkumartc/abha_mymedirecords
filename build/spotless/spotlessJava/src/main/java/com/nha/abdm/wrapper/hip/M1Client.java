/* (C) 2024 */
package com.nha.abdm.wrapper.hip;

import com.nha.abdm.wrapper.common.RequestManager;
import com.nha.abdm.wrapper.common.models.AuthData;
import com.nha.abdm.wrapper.common.models.AuthMethods;
import com.nha.abdm.wrapper.common.models.Consent1;
import com.nha.abdm.wrapper.common.models.Otp;
import com.nha.abdm.wrapper.common.models.RegistrationRequest;
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

  @Autowired LogsRepo logsRepo;
  private final RequestManager requestManager;
  @Autowired RequestLogService requestLogService;

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
  public FacadeResponse enrollABHA(LinkRecordsRequest linkRecordsRequest) {

    Otp otp =
        Otp.builder()
            .timeStamp(String.valueOf(System.currentTimeMillis())) // Current timestamp
            .txnId("4f3a4025-499c-4c80-8ccc-eec0ca256332")
            .otpValue(
                "bKGzd8IhJJDOk4Wckudgm/nkwEm96hvwsVu/NBTTqfrWetp4vb3rfeqZEWovofCVG42kzqH5zK5qWIPhw7BDd6AJ7AxZRxfYKiBb7oYbIONqC3lGa5dd4d5nYSsA600TS/4jEmpDeUscG5VE/mQqyfQlCtzT/SiJ1OVddRJDYviHQ1XU6yqy8Ri9x506kIN9ZCklpPlZTsgmiy+VHCg8J27O7igK/sRakrdWfWQQUP7SfM/UN0vcbdQxM8GiwY9dYXwAfJZzHA9uQ+EMfLwZ4Z45lg4427bXjr22lkaCjK/sFrHGZu5AzxLu6ob1TY/b34Y220Z38cbYpRX4BO//xmRP5vP5xHjIMN0TnqX0T7w1vuHQWHtaYouy5MLAZLslg6NEsdpraeI2+ADTOd6esSBqVatLH5jfaJ+BxJR5XsY/CkIGpURiUL1/J66M/orYEPYAZNaIqXTib2I3ar+tZ1663zWiD/1FE+rmZzL28swlARm8xEmHI0+z0CTwkPW+m0Ql6120wsNzAyfH1z4PQ58BP30e/1mH9q+oXWDODMNvMcZwdeN1q1PFlvL1wZBnFFBUrt9qAOr4/+HCynvt3lOUx3him4crmMAh4/AxwJRZo6n4yrPMdTRUtLD97oNWfDrl+o/OsW04zO4dSiwKDQmHVWpXJt/Oi49uvrmfNzI=")
            .mobile("7999999999")
            .build();

    Consent1 consent = Consent1.builder().code("abha-enrollment").version("1.4").build();

    AuthMethods authMethods = AuthMethods.builder().authMethods(Arrays.asList("otp")).build();

    AuthData authData = AuthData.builder().authMethods(authMethods).otp(otp).build();

    RegistrationRequest registrationRequest =
        RegistrationRequest.builder().authData(authData).consent(consent).build();

    log.debug("LinkAuthInit : " + registrationRequest.toString());

    try {
      ResponseEntity<GenericResponse> response =
          requestManager.fetchResponseFromGateway(enrollABHA, registrationRequest);
      log.debug(enrollABHA + " : enrollABHA: " + response.getStatusCode());
      if (response.getStatusCode() == HttpStatus.ACCEPTED) {

      } else if (Objects.nonNull(response.getBody())
          && Objects.nonNull(response.getBody().getErrorResponse())) {

        return FacadeResponse.builder()
            .error(response.getBody().getErrorResponse())
            .clientRequestId(linkRecordsRequest.getRequestId())
            .code(response.getStatusCode().value())
            .build();
      }
      return FacadeResponse.builder()
          .clientRequestId(linkRecordsRequest.getRequestId())
          .code(response.getStatusCode().value())
          .build();
    } catch (WebClientResponseException.BadRequest ex) {
      ErrorResponse error = ex.getResponseBodyAs(ErrorResponseWrapper.class).getError();
      log.error("HTTP error {}: {}", ex.getStatusCode(), error);
      return FacadeResponse.builder()
          .clientRequestId(linkRecordsRequest.getRequestId())
          .error(error)
          .httpStatusCode(HttpStatus.BAD_REQUEST)
          .build();
    } catch (Exception ex) {
      String error =
          "Exception while Initiating HIP auth: "
              + ex.getMessage()
              + " unwrapped exception: "
              + Exceptions.unwrap(ex);
      log.debug(error);
      return FacadeResponse.builder()
          .message(error)
          .clientRequestId(linkRecordsRequest.getRequestId())
          .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
          .build();
    }
  }
}
