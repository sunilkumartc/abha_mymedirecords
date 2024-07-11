package com.nha.abdm.wrapper.common.responses;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdhaarResponse {
	 private String txnId;
	  private String mobileNumber;
	  private HttpStatusCode httpStatusCode;
	  private ErrorResponse error;


}
