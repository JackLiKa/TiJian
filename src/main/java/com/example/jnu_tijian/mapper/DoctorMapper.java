package com.example.jnu_tijian.mapper;

import com.example.jnu_tijian.entity.Doctor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DoctorMapper {
    int deleteByPrimaryKey(Integer docId);

    int insert(Doctor record);

    int insertSelective(Doctor record);


    Doctor doctorLogin(String docId, String password);

    Doctor selectByPrimaryKey(Integer docId);

    //注解开发
    @Select("select docId, docCode, realName, password, sex, deptno from doctor where docCode=#{docCode}")
    Doctor selectByDocCode(Doctor doctor);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
}