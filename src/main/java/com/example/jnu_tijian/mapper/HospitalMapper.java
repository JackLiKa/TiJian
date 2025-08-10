package com.example.jnu_tijian.mapper;


import com.example.jnu_tijian.entity.Hospital;

import java.util.List;

public interface HospitalMapper {
    List<Hospital> selectAll();//查询所有正在营业的医院信息

    Hospital selectByPrimaryKey(Integer hpId);

    String getHospitalNameByHpId(Integer hpId); //根据医院ID获取医院名称


}
