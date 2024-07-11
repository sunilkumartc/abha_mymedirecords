/* (C) 2024 */
package com.nha.abdm.wrapper.hip.hrp.discover.requests;

import com.nha.abdm.wrapper.common.models.RespRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnDiscoverRequest {

  private String requestId;
  private String timestamp;
  private String transactionId;
  private OnDiscoverPatient patient;
  private RespRequest resp;
}
