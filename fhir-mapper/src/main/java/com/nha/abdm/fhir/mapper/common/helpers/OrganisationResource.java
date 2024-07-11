/* (C) 2024 */
package com.nha.abdm.fhir.mapper.common.helpers;

import com.nha.abdm.fhir.mapper.exceptions.NotBlankFields;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NotBlankFields
public class OrganisationResource {
  private String facilityName;
  private String facilityId;
}
