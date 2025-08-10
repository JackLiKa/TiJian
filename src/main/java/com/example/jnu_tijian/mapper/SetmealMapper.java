package com.example.jnu_tijian.mapper;

import com.example.jnu_tijian.entity.Setmeal;

import java.util.List;

public interface SetmealMapper {

    List<Setmeal>listSetmeal(Integer sex);

    int deleteByPrimaryKey(Integer smId);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    Setmeal selectByPrimaryKey(Integer smId);

    List<Setmeal> selectBySex(Integer sex);

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);

    String getSetmealNameBySmId(Integer smId); //根据套餐ID获取套餐名称

}
