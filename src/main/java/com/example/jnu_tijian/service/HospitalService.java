package com.example.jnu_tijian.service;

import com.example.jnu_tijian.entity.Hospital;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;


    public ResponseObject listHospital(){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,hospitalMapper.selectAll());
    }


    public ResponseObject loadHospital(Hospital hospital) {
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,hospitalMapper.selectByPrimaryKey(hospital.getHpId()));
    }

    public ResponseObject getHospitalNameByHpId(Integer hpId) {
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,hospitalMapper.getHospitalNameByHpId(hpId));
    }

}
