package com.example.jnu_tijian.controller;


import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Doctor;
import com.example.jnu_tijian.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @RequestMapping("/doctorLogin")
    public ResponseObject login(@RequestBody Doctor doctor){
        return doctorService.doctorLogin(doctor);
    }


}
