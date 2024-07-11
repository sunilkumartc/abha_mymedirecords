/* (C) 2024 */
package com.nha.abdm.wrapper.patient;

import com.nha.abdm.wrapper.common.models.RegistrationRequest;
import com.nha.abdm.wrapper.common.responses.AdhaarResponse;
import com.nha.abdm.wrapper.hip.M1Client;
import com.nha.abdm.wrapper.hip.hrp.database.mongo.services.PatientService;
import com.nha.abdm.wrapper.hip.hrp.database.mongo.tables.Patient;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/patient")
public class PatientController {
  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }
  
  @Autowired M1Client m1Client;

  @GetMapping({"/{patientId}"})
  public ResponseEntity<Patient> getPatientDetails(@PathVariable("patientId") String patientId) {
    Patient patient = patientService.getPatientDetails(patientId);
    if (Objects.isNull(patient)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(patient, HttpStatus.OK);
  }

  
  @PostMapping("/generate/byAadhaar/{otp}") 
  public ResponseEntity<AdhaarResponse> enrollByAadhaar(@PathVariable("otp") String otp) 
  { 
	  
	  AdhaarResponse adhaarResponse  = m1Client.enrollABHA(otp);
	  
	  return new ResponseEntity<>(adhaarResponse, adhaarResponse.getHttpStatusCode());
	  
	  }
  
  @PostMapping("/verify/otp/{otp}") 
  public ResponseEntity<String> verifyOtp(@PathVariable("otp") String otp) 
  { 
	  
	  String response =  m1Client.verifyOTP(otp);
	  
	  return new ResponseEntity<>(response, HttpStatus.OK);
	  
	  }
}
