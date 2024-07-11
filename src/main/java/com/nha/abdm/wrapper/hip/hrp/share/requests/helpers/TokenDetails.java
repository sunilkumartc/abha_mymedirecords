/* (C) 2024 */
package com.nha.abdm.wrapper.hip.hrp.share.requests.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenDetails {
  private String abhaAddress;
  private String hipCounterCode;
  private String hipId;
}
