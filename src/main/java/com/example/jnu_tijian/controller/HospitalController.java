package com.example.jnu_tijian.controller;


import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Hospital;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.exception.TijianAppException;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.HospitalMapper;

import com.example.jnu_tijian.mapper.UsersMapper;
import com.example.jnu_tijian.service.HospitalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {
    @Autowired
    HospitalService hospitalService;


    //获取所有状态为正常的医院的列表数据
    @RequestMapping("/listHospital")
    public ResponseObject listHospital() {
        System.out.println("收到 /listHospital 请求");
       return hospitalService.listHospital();
    }

    @RequestMapping("/loadHospital")
    public ResponseObject loadHospital(@RequestBody Hospital hospital) {
        return hospitalService.loadHospital(hospital);
    }

    @PostMapping("/getHospitalNameByHpId")
    public ResponseObject getHospitalNameByHpId(@RequestBody Hospital hospital) {
        System.out.println("前端传来hpId"+hospital.getHpId());
        return hospitalService.getHospitalNameByHpId(hospital.getHpId());
    }
}
