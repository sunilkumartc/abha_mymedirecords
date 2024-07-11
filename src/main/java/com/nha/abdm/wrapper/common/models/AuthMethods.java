/* (C) 2024 */
package com.nha.abdm.wrapper.common.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthMethods {
  private List<String> authMethods;
}
