package com.satya.app.repository;
import com.satya.app.model.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PrescriptionRepository extends MongoRepository<Prescription,String>
{

     List<Prescription> findByPatientName(String patientName);

}
