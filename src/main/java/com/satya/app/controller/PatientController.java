package com.satya.app.controller;
import com.satya.app.model.Appointment;
import com.satya.app.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/myappointment")
    public List<Appointment> getMyAppointments(@RequestParam String patientName){
        return appointmentRepository.findByPatientName(patientName);
    }

    @PostMapping("/save")
    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }
}
