package com.satya.app.controller;
import com.satya.app.model.Appointment;
import com.satya.app.model.Prescription;
import com.satya.app.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {
    @InjectMocks
    private PatientController patientController;
    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    public void getMyAppointments() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("appointment1");
        appointment.setDate("28/09/2022");
        appointment.setPatientName("patient1");
        appointment.setPrescription(new Prescription("prescription1","appointment1","description1","patient1","doctor1"));
        appointment.setDoctorName("doctor1");
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(appointmentRepository.findByPatientName(anyString())).thenReturn(appointmentList);
        List<Appointment> res = patientController.getMyAppointments("patient1");
        assertEquals(res.get(0).getAppointmentId(),appointmentList.get(0).getAppointmentId());
        assertEquals(res.get(0).getPrescription(),appointmentList.get(0).getPrescription());
        assertEquals(res.get(0).getDate(),appointmentList.get(0).getDate());
        assertEquals(res.get(0).getPatientName(),appointmentList.get(0).getPatientName());
        assertEquals(res.get(0).getDoctorName(),appointmentList.get(0).getDoctorName());

    }

    @Test
    public void saveAppointment() {
        Appointment mockAppointment = mock(Appointment.class);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("appoint1");
        appointment.setDate("06/09/2022");
        appointment.setPatientName("patient1");
        appointment.setPrescription(new Prescription("presc1","appoint1","desc","patient1","doctor1"));
        appointment.setDoctorName("doctor1");
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
        Appointment res = patientController.saveAppointment(mockAppointment);
        assertEquals(res.getPrescription(),appointment.getPrescription());
        assertEquals(res.getDate(),appointment.getDate());
        assertEquals(res.getPatientName(),appointment.getPatientName());
        assertEquals(res.getDoctorName(),appointment.getDoctorName());
        assertEquals(res.getAppointmentId(),appointment.getAppointmentId());
    }
}