/* (C) 2024 */
package com.nha.abdm.fhir.mapper.requests;

import com.nha.abdm.fhir.mapper.common.helpers.DocumentResource;
import com.nha.abdm.fhir.mapper.common.helpers.OrganisationResource;
import com.nha.abdm.fhir.mapper.common.helpers.PatientResource;
import com.nha.abdm.fhir.mapper.common.helpers.PractitionerResource;
import com.nha.abdm.fhir.mapper.requests.helpers.ObservationResource;
import com.nha.abdm.fhir.mapper.requests.helpers.WellnessObservationResource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WellnessRecordRequest {
  @Pattern(regexp = "WellnessRecord")
  @NotBlank(message = "BundleType is mandatory and must not be empty : 'WellnessRecord'")
  private String bundleType;

  @NotBlank(message = "careContextReference is mandatory and must not be empty")
  private String careContextReference;

  @Valid
  @NotNull(message = "Patient demographic details are mandatory and must not be empty") private PatientResource patient;

  private String encounter;

  @Pattern(
      regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z)?$",
      message = "Value must match either yyyy-MM-dd or yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  @NotBlank(message = "authoredOn is mandatory timestamp")
  private String authoredOn;

  @Valid
  @NotNull(message = "practitioners are mandatory and must not be empty") private List<PractitionerResource> practitioners;

  @Valid
  @NotNull(message = "organisation is mandatory") private OrganisationResource organisation;

  @Valid private List<WellnessObservationResource> vitalSigns;
  @Valid private List<WellnessObservationResource> bodyMeasurements;
  @Valid private List<WellnessObservationResource> physicalActivities;
  @Valid private List<WellnessObservationResource> generalAssessments;
  @Valid private List<WellnessObservationResource> womanHealths;
  @Valid private List<WellnessObservationResource> lifeStyles;
  @Valid private List<ObservationResource> otherObservations;
  @Valid private List<DocumentResource> documents;
}
