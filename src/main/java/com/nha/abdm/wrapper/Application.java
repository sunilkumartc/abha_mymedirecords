package com.nha.abdm.wrapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.nha.abdm.wrapper.common.RequestManager;
import com.nha.abdm.wrapper.common.models.AuthData;
import com.nha.abdm.wrapper.common.models.AuthMethods;
import com.nha.abdm.wrapper.common.models.Consent1;
import com.nha.abdm.wrapper.common.models.Otp;
import com.nha.abdm.wrapper.common.models.RegistrationRequest;
import com.nha.abdm.wrapper.common.responses.GenericResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    private static final Logger log = LogManager.getLogger(Application.class);

  /**  public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }**/

   /** @Bean
    CommandLineRunner run(RequestManager requestManager) {
        return args -> {
            Otp otp = Otp.builder()
                    .timeStamp(String.valueOf(System.currentTimeMillis())) // Current timestamp
                    .txnId("7c3b4870-353d-4b35-9b6c-ce7635f8ce23")
                    .otpValue("k35MhA76OMzTsuiSePYMEHchfXca+3cjgnfVRTKK5oz+a/9AasPEyL3CiD2hN4A1+WSPUoKMu0VnbnYuF0RtWh5zwJNogQWsAXkCSJ4o4F3DU/Lssq3hcpCBm585Vks3ZA3Mgae+R4tFuCNTVYrUVb/MYE0BCnQoVrlByraDOzWFuTrfzV5adjLxE8cFTfzN9Mmupilv0b8CDeyax5ZF//GZESrpKUiOgOlglZ4dR21J6XnPHSUcaipDolRVXePwPsv/ZhPN03UFPvFhwRP82/ENS+0NZHdMCwh4skz/Hpw/YrGcCGY/VrccQ4xWKgco2J4jBbkxBXospdecNWAE/PUeZtXskRRCDgCc4jAnzM1o9UlbQH0PIgs/+Czg+Mcri3rkB6wgK9L09MbVl8Z/9XfFdiu6Hd1m+wIWiSXaYvKK82/Mi3n+XJpTMSw6wuQhRcIZ2DaxaOqqmxxCJKbyrGCzSeUS2fdHDNRRlIoPfxZT8+XOZX9SgNkMDijEkZK7Xuhc1wKHggU/lEKbkkB4j0jf3DXy9i59jy6fOZsYuePjJyxaDvDdwh9SLCCAsPj5pTGUPDFIcu2Ti3IFAGNAaRm206qjKzbjr7wBkVUYCtJwNlRuR6KkvK9kX4bpQ9JBlq9Y5+Stzma1cEAhauQdd6oGf9cEz+APcGhcW7dqUrA=")
                    .mobile("9880020224")
                    .build();

            Consent1 consent = Consent1.builder().code("abha-enrollment").version("1.4").build();

            AuthMethods authMethods = AuthMethods.builder().authMethods(Arrays.asList("otp")).build();

            AuthData authData = AuthData.builder().authMethods(authMethods).otp(otp).build();

            RegistrationRequest registrationRequest = RegistrationRequest.builder()
                    .authData(authData)
                    .consent(consent)
                    .build();

            log.debug("LinkAuthInit : " + registrationRequest.toString());

            try {
                ResponseEntity<GenericResponse> response = requestManager.fetchResponseFromGateway("/api/v3/enrollment/enrol/byAadhaar", registrationRequest);
                log.info("Response: " + response.getBody());
            } catch (Exception e) {
                log.error("Error occurred: ", e);
            }
        };
    }**/
}

