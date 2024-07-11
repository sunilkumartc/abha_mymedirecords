/* (C) 2024 */
package com.nha.abdm.wrapper.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
  private String timeStamp;
  private String txnId;
  private String otpValue;
  private String mobile;
}
