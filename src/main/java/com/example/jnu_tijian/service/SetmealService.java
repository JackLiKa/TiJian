package com.example.jnu_tijian.service;

import com.example.jnu_tijian.entity.Checkitem;
import com.example.jnu_tijian.entity.Setmeal;
import com.example.jnu_tijian.entity.Setmealdetailed;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.CheckitemMapper;
import com.example.jnu_tijian.mapper.SetmealMapper;
import com.example.jnu_tijian.mapper.SetmealdetailedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealService{
    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    SetmealdetailedMapper setmealdetailedMapper;

    @Autowired
    CheckitemMapper checkitemMapper;

    //根据性别获取套餐列表数据，同时把每个套餐关联的检查项目数据也查询出来
    public ResponseObject listSetmeal(Integer sex){
        //1.首先根据性别查询所有对应的套餐数据
        List<Setmeal> setmealList=setmealMapper.selectBySex(sex);
        for(int k=0;k<setmealList.size();k++) {
            System.out.println(setmealList.get(k).toString());
        }
        System.out.println("setmealist数据加载成功");


        //2.拿着套餐编号查询都有哪些检查项，再根据检查项编号查询检查项的详细信息
         for(int i=0;i<setmealList.size();i++){

             Setmeal setmeal=setmealList.get(i);  //拿到每个套餐


             List<Setmealdetailed> setmealdetailedList=setmealdetailedMapper.selectBySetmealId(setmeal.getSmId());

                //定义一个容器：存放当前套餐对应的检查项集合
             List<Checkitem> checkitemList=new ArrayList<>();

             //再根据检查项编号查询检查项的详细信息
              for(Setmealdetailed sd:setmealdetailedList){
                  Integer ciId=sd.getCiId();
                  Checkitem checkitem=checkitemMapper.selectByPrimaryKey(ciId);
                  checkitemList.add(checkitem);
              }

            setmeal.setCheckitemList(checkitemList);

         }

//         打印输出一下
        for(int i=0;i<setmealList.size();i++) {
        System.out.println(setmealList.get(i).toString());
        }



        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,setmealList);
    }

    public ResponseObject loadSetmeal(Setmeal setmeal) {
        // 示例逻辑：从数据库加载套餐信息
        Setmeal result = setmealMapper.selectByPrimaryKey(setmeal.getSmId());
        if (result != null) {
            return new ResponseObject(  ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, result);
        } else {
            return new ResponseObject(TijianAppExceptionEnum.LOAD_MEAL_ERROR.getCode(), TijianAppExceptionEnum.LOAD_MEAL_ERROR.getMessage(), null);
        }

//        return new ResponseObject(ResponseObject.SUCCESS_STATUS,setmealMapper.selectByPrimaryKey(setmeal.getSmId()));
    }

    public ResponseObject getSetmealNameBySmId(Integer smId) {
        // 调用mapper方法获取套餐名称
        String setmealName = setmealMapper.getSetmealNameBySmId(smId);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, setmealName);
    }

}
