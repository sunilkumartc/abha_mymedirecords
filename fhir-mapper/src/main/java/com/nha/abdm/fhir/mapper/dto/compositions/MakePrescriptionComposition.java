/* (C) 2024 */
package com.nha.abdm.fhir.mapper.dto.compositions;

import com.nha.abdm.fhir.mapper.Utils;
import com.nha.abdm.fhir.mapper.common.constants.BundleResourceIdentifier;
import com.nha.abdm.fhir.mapper.common.constants.BundleUrlIdentifier;
import com.nha.abdm.fhir.mapper.common.constants.ResourceProfileIdentifier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

@Service
public class MakePrescriptionComposition {
  public Composition makeCompositionResource(
      Patient patient,
      List<Practitioner> practitionerList,
      Organization organization,
      String authoredOn,
      Encounter encounter,
      List<MedicationRequest> medicationRequestList,
      List<Binary> documentList)
      throws ParseException {
    Composition composition = new Composition();
    Meta meta = new Meta();
    meta.setVersionId("1");
    meta.setLastUpdated(Utils.getCurrentTimeStamp());
    meta.addProfile(ResourceProfileIdentifier.PROFILE_PRESCRIPTION_RECORD);
    composition.setMeta(meta);
    CodeableConcept typeCode = new CodeableConcept();
    Coding typeCoding = new Coding();
    typeCoding.setSystem(BundleUrlIdentifier.SNOMED_URL);
    typeCoding.setCode("440545006");
    typeCoding.setDisplay("Prescription record");
    typeCode.addCoding(typeCoding);
    composition.setType(typeCode);
    composition.setTitle("Prescription record");
    if (Objects.nonNull(organization))
      composition.setCustodian(
          new Reference()
              .setReference(BundleResourceIdentifier.ORGANISATION + "/" + organization.getId()));
    if (Objects.nonNull(encounter))
      composition.setEncounter(
          new Reference()
              .setReference(BundleResourceIdentifier.ENCOUNTER + "/" + encounter.getId())
              .setDisplay(encounter.getClass_().getDisplay()));
    List<Reference> authorList = new ArrayList<>();
    HumanName practitionerName = null;
    for (Practitioner author : practitionerList) {
      practitionerName = author.getName().get(0);
      authorList.add(
          new Reference()
              .setReference(BundleResourceIdentifier.PRACTITIONER + "/" + author.getId())
              .setDisplay(practitionerName.getText()));
    }
    composition.setAuthor(authorList);
    HumanName patientName = patient.getName().get(0);
    composition.setSubject(
        new Reference()
            .setReference(BundleResourceIdentifier.PATIENT + "/" + patient.getId())
            .setDisplay(patientName.getText()));
    composition.setDateElement(Utils.getFormattedDateTime(authoredOn));
    Composition.SectionComponent medicationComponent = new Composition.SectionComponent();
    medicationComponent.setTitle("Medications");
    medicationComponent.setCode(
        new CodeableConcept()
            .setText("Prescription record")
            .addCoding(
                new Coding()
                    .setCode("440545006")
                    .setDisplay("Prescription record")
                    .setSystem(BundleUrlIdentifier.SNOMED_URL)));
    for (MedicationRequest medicationRequest : medicationRequestList) {
      Reference entryReference =
          new Reference()
              .setReference(
                  BundleResourceIdentifier.MEDICATION_REQUEST + "/" + medicationRequest.getId())
              .setType("MedicationRequest");
      medicationComponent.addEntry(entryReference);
    }
    composition.addSection(medicationComponent);
    for (Binary binary : documentList)
      medicationComponent.addEntry(
          new Reference().setReference("Binary/" + binary.getId()).setType("Binary"));
    composition.setStatus(Composition.CompositionStatus.FINAL);
    Identifier identifier = new Identifier();
    identifier.setSystem(BundleUrlIdentifier.WRAPPER_URL);
    identifier.setValue(UUID.randomUUID().toString());
    composition.setIdentifier(identifier);
    composition.setId(UUID.randomUUID().toString());
    return composition;
  }
}
