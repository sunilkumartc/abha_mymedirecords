/* (C) 2024 */
package com.nha.abdm.wrapper.hip.hrp.dataTransfer.callback;

import com.nha.abdm.wrapper.common.requests.HealthInformationRequest;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HIPHealthInformationRequest implements Serializable {

  private static final long serialVersionUID = 165269402517398406L;

  private String requestId;
  private String timestamp;
  private String transactionId;
  private HealthInformationRequest hiRequest;
}
