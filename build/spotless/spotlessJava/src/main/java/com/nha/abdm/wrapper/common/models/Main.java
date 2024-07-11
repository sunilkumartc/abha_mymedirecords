/* (C) 2024 */
package com.nha.abdm.wrapper.common.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

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

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String json = objectMapper.writeValueAsString(registrationRequest);
      System.out.println("Registration Request JSON: " + json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    System.out.print(registrationRequest.toString());
  }
}
