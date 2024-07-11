/* (C) 2024 */
package com.nha.abdm.wrapper.common.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IdRequest {
  private String id;
}
