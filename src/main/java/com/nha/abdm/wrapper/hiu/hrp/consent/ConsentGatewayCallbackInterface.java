/* (C) 2024 */
package com.nha.abdm.wrapper.hiu.hrp.consent;

import com.nha.abdm.wrapper.common.exceptions.IllegalDataStateException;
import com.nha.abdm.wrapper.hiu.hrp.consent.requests.callback.*;
import org.springframework.http.HttpStatus;

public interface ConsentGatewayCallbackInterface {
  HttpStatus onInitConsent(OnInitRequest onInitRequest) throws IllegalDataStateException;

  HttpStatus consentOnStatus(HIUConsentOnStatusRequest HIUConsentOnStatusRequest)
      throws IllegalDataStateException;

  HttpStatus hiuNotify(NotifyHIURequest notifyHIURequest) throws IllegalDataStateException;

  HttpStatus consentOnFetch(OnFetchRequest onFetchRequest) throws IllegalDataStateException;
}
