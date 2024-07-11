/* (C) 2024 */
package com.nha.abdm.fhir.mapper.converter;

import com.nha.abdm.fhir.mapper.Utils;
import com.nha.abdm.fhir.mapper.common.constants.BundleResourceIdentifier;
import com.nha.abdm.fhir.mapper.common.helpers.*;
import com.nha.abdm.fhir.mapper.dto.compositions.MakeImmunizationComposition;
import com.nha.abdm.fhir.mapper.dto.resources.*;
import com.nha.abdm.fhir.mapper.requests.ImmunizationRequest;
import com.nha.abdm.fhir.mapper.requests.helpers.ImmunizationResource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

@Service
public class ImmunizationConverter {
  private final MakeDocumentResource makeDocumentReference;
  private final MakePatientResource makePatientResource;
  private final MakePractitionerResource makePractitionerResource;
  private final MakeOrganisationResource makeOrganisationResource;
  private final MakeImmunizationResource makeImmunizationResource;
  private final MakeBundleMetaResource makeBundleMetaResource;
  private final MakeEncounterResource makeEncounterResource;
  private final MakeImmunizationComposition makeImmunizationComposition;
  private String docName = "Immunization record";
  private String docCode = "41000179103";

  public ImmunizationConverter(
      MakeDocumentResource makeDocumentReference,
      MakePatientResource makePatientResource,
      MakePractitionerResource makePractitionerResource,
      MakeOrganisationResource makeOrganisationResource,
      MakeImmunizationResource makeImmunizationResource,
      MakeBundleMetaResource makeBundleMetaResource,
      MakeEncounterResource makeEncounterResource,
      MakeImmunizationComposition makeImmunizationComposition) {
    this.makeDocumentReference = makeDocumentReference;
    this.makePatientResource = makePatientResource;
    this.makePractitionerResource = makePractitionerResource;
    this.makeOrganisationResource = makeOrganisationResource;
    this.makeImmunizationResource = makeImmunizationResource;
    this.makeBundleMetaResource = makeBundleMetaResource;
    this.makeEncounterResource = makeEncounterResource;
    this.makeImmunizationComposition = makeImmunizationComposition;
  }

  public BundleResponse makeImmunizationBundle(ImmunizationRequest immunizationRequest)
      throws ParseException {
    try {
      Bundle bundle = new Bundle();
      Patient patient = makePatientResource.getPatient(immunizationRequest.getPatient());
      List<Practitioner> practitionerList =
          Optional.ofNullable(immunizationRequest.getPractitioners())
              .map(
                  practitioners ->
                      practitioners.stream()
                          .map(
                              practitioner -> {
                                try {
                                  return makePractitionerResource.getPractitioner(practitioner);
                                } catch (ParseException e) {
                                  throw new RuntimeException(e);
                                }
                              })
                          .collect(Collectors.toList()))
              .orElseGet(ArrayList::new);
      Organization organization =
          makeOrganisationResource.getOrganization(immunizationRequest.getOrganisation());
      Encounter encounter =
          makeEncounterResource.getEncounter(
              patient,
              immunizationRequest.getEncounter() != null
                  ? immunizationRequest.getEncounter()
                  : null,
              immunizationRequest.getAuthoredOn());
      List<Organization> manufactureList = new ArrayList<>();
      List<Immunization> immunizationList = new ArrayList<>();
      for (ImmunizationResource immunizationResource : immunizationRequest.getImmunizations()) {
        Organization manufacturer =
            makeOrganisationResource.getOrganization(
                OrganisationResource.builder()
                    .facilityId(immunizationResource.getManufacturer())
                    .facilityName(immunizationResource.getManufacturer())
                    .build());
        immunizationList.add(
            makeImmunizationResource.getImmunization(
                patient, practitionerList, manufacturer, immunizationResource));
        manufactureList.add(manufacturer);
      }
      List<DocumentReference> documentList = new ArrayList<>();
      if (immunizationRequest.getDocuments() != null) {
        for (DocumentResource documentResource : immunizationRequest.getDocuments()) {
          documentList.add(
              makeDocumentReference.getDocument(
                  patient, organization, documentResource, docCode, docName));
        }
      }
      Composition composition =
          makeImmunizationComposition.makeCompositionResource(
              patient,
              practitionerList,
              organization,
              immunizationRequest.getAuthoredOn(),
              immunizationList,
              documentList);
      bundle.setId(UUID.randomUUID().toString());
      bundle.setType(Bundle.BundleType.DOCUMENT);
      bundle.setTimestamp(Utils.getCurrentTimeStamp());
      bundle.setMeta(makeBundleMetaResource.getMeta());
      List<Bundle.BundleEntryComponent> entries = new ArrayList<>();
      bundle.setIdentifier(
          new Identifier()
              .setSystem("https://ABDM_WRAPPER/bundle")
              .setValue(immunizationRequest.getCareContextReference()));
      entries.add(
          new Bundle.BundleEntryComponent()
              .setFullUrl(BundleResourceIdentifier.COMPOSITION + "/" + composition.getId())
              .setResource(composition));
      entries.add(
          new Bundle.BundleEntryComponent()
              .setFullUrl(BundleResourceIdentifier.PATIENT + "/" + patient.getId())
              .setResource(patient));
      for (Practitioner practitioner : practitionerList) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(BundleResourceIdentifier.PRACTITIONER + "/" + practitioner.getId())
                .setResource(practitioner));
      }
      if (Objects.nonNull(organization)) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(BundleResourceIdentifier.ORGANISATION + "/" + organization.getId())
                .setResource(organization));
      }
      if (Objects.nonNull(encounter)) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(BundleResourceIdentifier.ENCOUNTER + "/" + encounter.getId())
                .setResource(encounter));
      }
      for (Organization manufacturer : manufactureList) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(BundleResourceIdentifier.MANUFACTURER + "/" + manufacturer.getId())
                .setResource(manufacturer));
      }
      for (Immunization immunization : immunizationList) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(BundleResourceIdentifier.IMMUNIZATION + "/" + immunization.getId())
                .setResource(immunization));
      }
      for (DocumentReference documentReference : documentList) {
        entries.add(
            new Bundle.BundleEntryComponent()
                .setFullUrl(
                    BundleResourceIdentifier.DOCUMENT_REFERENCE + "/" + documentReference.getId())
                .setResource(documentReference));
      }
      bundle.setEntry(entries);
      return BundleResponse.builder().bundle(bundle).build();
    } catch (Exception e) {
      return BundleResponse.builder()
          .error(ErrorResponse.builder().code(1000).message(e.getMessage()).build())
          .build();
    }
  }
}
