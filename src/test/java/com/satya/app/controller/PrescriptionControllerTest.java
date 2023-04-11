package com.satya.app.controller;
import com.satya.app.model.Prescription;
import com.satya.app.repository.PrescriptionRepository;
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
public class PrescriptionControllerTest {

    @InjectMocks
    private PrescriptionController presciptionController;

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @Test
    public void savePrescription() {
        Prescription mockPrescription = mock(Prescription.class);
        Prescription prescription= new Prescription();
        prescription.setPrescriptionId("prescription1");
        prescription.setDescription("description1");
        prescription.setDoctorName("doctor1");
        prescription.setPatientName("patient1");
        prescription.setAppointmentId("appointment1");
        List<Prescription> prescriptionList = new ArrayList<>();
        prescriptionList.add(prescription);
        when(prescriptionRepository.save(any(Prescription.class))).thenReturn(prescription);
        Prescription res = presciptionController.savePrescription(mockPrescription);
        assertEquals(res.getPrescriptionId(),prescriptionList.get(0).getPrescriptionId());
        assertEquals(res.getPatientName(),prescriptionList.get(0).getPatientName());
        assertEquals(res.getDoctorName(),prescriptionList.get(0).getDoctorName());
        assertEquals(res.getAppointmentId(),prescriptionList.get(0).getAppointmentId());
        assertEquals(res.getDescription(),prescriptionList.get(0).getDescription());
    }
    @Test
    public void getAllPrescriptions() {
        Prescription prescription= new Prescription();
        prescription.setPrescriptionId("prescription1");
        prescription.setDescription("description1");
        prescription.setDoctorName("doctor1");
        prescription.setPatientName("patient1");
        prescription.setAppointmentId("appointment1");
        List<Prescription> prescriptionList = new ArrayList<>();
        prescriptionList.add(prescription);
        when(prescriptionRepository.findByPatientName(anyString())).thenReturn(prescriptionList);
        List<Prescription> res = presciptionController.getAllPrescriptions("patient1");
        assertEquals(res.get(0).getPrescriptionId(),prescriptionList.get(0).getPrescriptionId());
        assertEquals(res.get(0).getAppointmentId(),prescriptionList.get(0).getAppointmentId());
        assertEquals(res.get(0).getPatientName(),prescriptionList.get(0).getPatientName());
        assertEquals(res.get(0).getDoctorName(),prescriptionList.get(0).getDoctorName());
        assertEquals(res.get(0).getDescription(),prescriptionList.get(0).getDescription());
    }
}