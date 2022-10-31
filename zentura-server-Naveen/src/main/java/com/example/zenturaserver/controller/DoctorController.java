package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.DoctorDTO;
import com.example.zenturaserver.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepo;

    // -----    GET      -----
    @GetMapping("/doctor/getall")
    public ResponseEntity<?> getAllDoctors() {
        List<DoctorDTO> doctors = doctorRepo.findAll();

        if (doctors.size() > 0) {
            return new ResponseEntity<List<DoctorDTO>>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Doctors found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctor/get/{id}")
    public ResponseEntity<?> getADoctor(@PathVariable("id") String id) {

        Optional<DoctorDTO> doctorOptional = doctorRepo.findById(id);

        if (doctorOptional.isPresent()) {
            return new ResponseEntity<DoctorDTO>(doctorOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor not found!", HttpStatus.NOT_FOUND);
        }

    }

    // -----    POST      -----
    @PostMapping("/doctor/create")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDTO doctor) {
        try {

            doctorRepo.save(doctor);
            return new ResponseEntity<DoctorDTO>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // -----    UPDATE      -----
    @PutMapping("/doctor/update/{id}")
    public ResponseEntity<?> updateADoctor(@PathVariable("id") String id, @RequestBody DoctorDTO doctor) {

        Optional<DoctorDTO> doctorOptional = doctorRepo.findById(id);

        if (doctorOptional.isPresent()) {
            DoctorDTO doctorToSave = doctorOptional.get();
            if(doctor.getFirstName()!=null && !doctor.getFirstName().isEmpty()){
                doctorToSave.setFirstName(doctor.getFirstName());
            }
            if(doctor.getLastName()!=null && !doctor.getLastName().isEmpty()){
                doctorToSave.setLastName(doctor.getLastName());
            }
            if(doctor.getDesignation()!=null && !doctor.getDesignation().isEmpty()){
                doctorToSave.setDesignation(doctor.getDesignation());
            }

            if(doctor.getChannellingTime()!=null && !doctor.getChannellingTime().isEmpty()){
                doctorToSave.setChannellingTime(doctor.getChannellingTime());
            }

            doctorRepo.save(doctorToSave);



            return new ResponseEntity<DoctorDTO>(doctorToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor not found!", HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<?> deleteAPatient(@PathVariable("id") String id) {
        try {
            doctorRepo.deleteById(id);
            return new ResponseEntity<>("Doctor successfully deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
