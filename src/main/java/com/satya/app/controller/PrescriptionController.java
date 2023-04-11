package com.satya.app.controller;
import com.satya.app.model.Prescription;
import com.satya.app.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping("/viewprescription")
    public List<Prescription> getAllPrescriptions(String patientName){
        return prescriptionRepository.findByPatientName(patientName);
    }

    @PostMapping("/saveprescription")
    public Prescription savePrescription(@RequestBody Prescription prescription){
        return prescriptionRepository.save(prescription);
    }
}
