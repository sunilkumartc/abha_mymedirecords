/* (C) 2024 */
package com.nha.abdm.fhir.mapper.common.helpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacadeError {
  private ErrorResponse error;
  private ValidationErrorResponse validationErrors;
}
