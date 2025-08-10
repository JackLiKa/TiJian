package com.example.jnu_tijian.mapper;

import com.example.jnu_tijian.entity.Checkitem;

public interface CheckitemMapper {
    int deleteByPrimaryKey(Integer ciId);

    int insert(Checkitem record);

    int insertSelective(Checkitem record);

    Checkitem selectByPrimaryKey(Integer ciId);

    int updateByPrimaryKeySelective(Checkitem record);

    int updateByPrimaryKey(Checkitem record);

}
