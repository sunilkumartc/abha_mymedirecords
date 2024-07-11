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
public class MakeHealthDocumentComposition {
  public Composition makeCompositionResource(
      Patient patient,
      String authoredOn,
      List<Practitioner> practitionerList,
      Organization organization,
      Encounter encounter,
      List<DocumentReference> documentReferenceList)
      throws ParseException {
    Composition composition = new Composition();
    Meta meta = new Meta();
    meta.setVersionId("1");
    meta.setLastUpdated(Utils.getCurrentTimeStamp());
    meta.addProfile(ResourceProfileIdentifier.PROFILE_HEALTH_DOCUMENT_RECORD);
    composition.setMeta(meta);
    Composition.SectionComponent sectionComponent = new Composition.SectionComponent();
    sectionComponent.setTitle("OPD Records");
    CodeableConcept typeCode = new CodeableConcept();
    Coding typeCoding = new Coding();
    typeCoding.setSystem(BundleUrlIdentifier.SNOMED_URL);
    typeCoding.setCode("419891008");
    typeCoding.setDisplay("Record artifact");
    typeCode.addCoding(typeCoding);
    typeCode.setText("Record artifact");
    composition.setType(typeCode);
    sectionComponent.setCode(typeCode);
    for (DocumentReference documentReference : documentReferenceList) {
      sectionComponent.addEntry(
          new Reference()
              .setReference(
                  BundleResourceIdentifier.DOCUMENT_REFERENCE + "/" + documentReference.getId()));
    }
    composition.addSection(sectionComponent);
    composition.setTitle("Health Document");
    composition.setEncounter(
        new Reference().setReference(BundleResourceIdentifier.ENCOUNTER + "/" + encounter.getId()));
    List<Reference> authorList = new ArrayList<>();
    for (Practitioner practitioner : practitionerList) {
      HumanName practionerName = practitioner.getName().get(0);
      authorList.add(
          new Reference()
              .setDisplay(practionerName.getText())
              .setReference(BundleResourceIdentifier.PRACTITIONER + "/" + practitioner.getId()));
    }
    if (Objects.nonNull(organization)) {
      composition.setCustodian(
          new Reference()
              .setDisplay(organization.getName())
              .setReference(BundleResourceIdentifier.ORGANISATION + "/" + organization.getId()));
    }
    composition.setAuthor(authorList);
    HumanName patientName = patient.getName().get(0);
    composition.setSubject(
        new Reference()
            .setDisplay(patientName.getText())
            .setReference(BundleResourceIdentifier.PATIENT + "/" + patient.getId()));
    composition.setDateElement(Utils.getFormattedDateTime(authoredOn));
    composition.setStatus(Composition.CompositionStatus.FINAL);
    Identifier identifier = new Identifier();
    identifier.setSystem(BundleUrlIdentifier.WRAPPER_URL);
    identifier.setValue(UUID.randomUUID().toString());
    composition.setIdentifier(identifier);
    composition.setId(UUID.randomUUID().toString());
    return composition;
  }
}
