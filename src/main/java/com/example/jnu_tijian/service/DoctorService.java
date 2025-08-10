package com.example.jnu_tijian.service;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Doctor;
import com.example.jnu_tijian.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    /**
     处理登录业务的方法
     */


    public ResponseObject doctorLogin(Doctor doctor){
        //doctorMapper.selectByPrimaryKey()
        Doctor result= doctorMapper.doctorLogin(doctor.getDocId(),doctor.getPassword());

        if(result==null){
            //抛出异常
            System.out.println("没有找到该名医生");
            return new ResponseObject(3001,"您尚未注册");
        }else{
            return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,result);
        }
    }

}
