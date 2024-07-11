/* (C) 2024 */
package com.nha.abdm.fhir.mapper.controller;

import ca.uhn.fhir.context.FhirContext;
import com.nha.abdm.fhir.mapper.common.helpers.BundleResponse;
import com.nha.abdm.fhir.mapper.common.helpers.ErrorResponse;
import com.nha.abdm.fhir.mapper.common.helpers.FacadeError;
import com.nha.abdm.fhir.mapper.converter.*;
import com.nha.abdm.fhir.mapper.requests.*;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/bundle")
@Validated
public class BundleController {
  @Autowired ImmunizationConverter immunizationConverter;
  @Autowired PrescriptionConverter prescriptionConverter;
  @Autowired HealthDocumentConverter healthDocumentConverter;
  @Autowired OPConsultationConverter opConsultationConverter;
  @Autowired DiagnosticReportConverter diagnosticReportConverter;
  @Autowired DischargeSummaryConverter dischargeSummaryConverter;
  @Autowired WellnessRecordConverter wellnessRecordConverter;
  FhirContext ctx = FhirContext.forR4();

  /**
   * @param immunizationRequest which has immunization details like vaccine and type of vaccine
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/immunization")
  public Object createImmunizationBundle(
      @Validated @RequestBody ImmunizationRequest immunizationRequest) throws ParseException {
    if (!immunizationRequest.getBundleType().equalsIgnoreCase("ImmunizationRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + immunizationRequest.getBundleType()
                                  + "'"
                                  + " required: ImmunizationRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        immunizationConverter.makeImmunizationBundle(immunizationRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param prescriptionRequest which has prescription details like medicine and dosage
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/prescription")
  public ResponseEntity<Object> createPrescriptionBundle(
      @Valid @RequestBody PrescriptionRequest prescriptionRequest) throws ParseException {
    if (!prescriptionRequest.getBundleType().equalsIgnoreCase("PrescriptionRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + prescriptionRequest.getBundleType()
                                  + "'"
                                  + " required: PrescriptionRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        prescriptionConverter.convertToPrescriptionBundle(prescriptionRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param opConsultationRequest which has all basic details of the visit
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/op-consultation")
  public ResponseEntity<Object> createOPConsultationBundle(
      @Valid @RequestBody OPConsultationRequest opConsultationRequest) throws ParseException {
    if (Objects.isNull(opConsultationRequest)
        || !opConsultationRequest.getBundleType().equalsIgnoreCase("OPConsultRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + opConsultationRequest.getBundleType()
                                  + "'"
                                  + " required: OPConsultRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        opConsultationConverter.convertToOPConsultationBundle(opConsultationRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param healthDocumentRecord which has document as an attachment
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/health-document")
  public ResponseEntity<Object> createHealthDocumentBundle(
      @Valid @RequestBody HealthDocumentRecord healthDocumentRecord) throws ParseException {
    if (Objects.isNull(healthDocumentRecord)
        || !healthDocumentRecord.getBundleType().equalsIgnoreCase("HealthDocumentRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + healthDocumentRecord.getBundleType()
                                  + "'"
                                  + " required: HealthDocumentRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        healthDocumentConverter.convertToHealthDocumentBundle(healthDocumentRecord);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param diagnosticReportRequest which has diagnostic details like the result and type of
   *     diagnosis
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping(value = "/diagnostic-report")
  public ResponseEntity<Object> createDiagnosticReportBundle(
      @Valid @RequestBody DiagnosticReportRequest diagnosticReportRequest) throws ParseException {
    if (Objects.isNull(diagnosticReportRequest)
        || !diagnosticReportRequest.getBundleType().equalsIgnoreCase("DiagnosticReportRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + diagnosticReportRequest.getBundleType()
                                  + "'"
                                  + " required: DiagnosticReportRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        diagnosticReportConverter.convertToDiagnosticBundle(diagnosticReportRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param dischargeSummaryRequest which has discharge details like the findings and observations
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/discharge-summary")
  public ResponseEntity<Object> createDischargeSummaryBundle(
      @Valid @RequestBody DischargeSummaryRequest dischargeSummaryRequest) throws ParseException {
    if (Objects.isNull(dischargeSummaryRequest)
        || !dischargeSummaryRequest.getBundleType().equalsIgnoreCase("DischargeSummaryRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + dischargeSummaryRequest.getBundleType()
                                  + "'"
                                  + " required: DischargeSummaryRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        dischargeSummaryConverter.convertToDischargeSummary(dischargeSummaryRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }

  /**
   * @param wellnessRecordRequest which has all the physical observations
   * @return FHIR bundle if no error found
   * @throws ParseException while parsing the string into date
   */
  @PostMapping("/wellness-record")
  public ResponseEntity<Object> createWellnessBundle(
      @Valid @RequestBody WellnessRecordRequest wellnessRecordRequest) throws ParseException {
    if (Objects.isNull(wellnessRecordRequest)
        || !wellnessRecordRequest.getBundleType().equalsIgnoreCase("WellnessRecord")) {
      return ResponseEntity.badRequest()
          .body(
              FacadeError.builder()
                  .error(
                      ErrorResponse.builder()
                          .code(1000)
                          .message(
                              "Incorrect bundleType: "
                                  + "'"
                                  + wellnessRecordRequest.getBundleType()
                                  + "'"
                                  + " required: WellnessRecord")
                          .build())
                  .build());
    }
    BundleResponse bundleResponse =
        wellnessRecordConverter.getWellnessBundle(wellnessRecordRequest);
    if (Objects.nonNull(bundleResponse.getError()))
      return ResponseEntity.badRequest()
          .body(FacadeError.builder().error(bundleResponse.getError()).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ctx.newJsonParser().encodeResourceToString(bundleResponse.getBundle()));
  }
}
