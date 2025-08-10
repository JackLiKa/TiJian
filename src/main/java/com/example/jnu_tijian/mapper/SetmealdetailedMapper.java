package com.example.jnu_tijian.mapper;

import com.example.jnu_tijian.entity.Setmealdetailed;

import java.util.List;

public interface SetmealdetailedMapper {
    int deleteByPrimaryKey(Integer sdId);

    int insert(Setmealdetailed record);

    int insertSelective(Setmealdetailed record);

    Setmealdetailed selectByPrimaryKey(Integer sdId);

    //根据套餐编号查询套餐明细数据
    List<Setmealdetailed> selectBySetmealId(Integer setmealId);

    int updateByPrimaryKeySelective(Setmealdetailed record);

    int updateByPrimaryKey(Setmealdetailed record);


}
