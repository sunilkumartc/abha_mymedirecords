/* (C) 2024 */
package com.nha.abdm.wrapper.common.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequest {
  private String clientId;
  private String clientSecret;
}
