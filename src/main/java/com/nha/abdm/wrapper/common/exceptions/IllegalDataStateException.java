/* (C) 2024 */
package com.nha.abdm.wrapper.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class IllegalDataStateException extends Exception {
  public IllegalDataStateException(String message) {
    super(message);
  }
}
