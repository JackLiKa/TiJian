package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Setmeal;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    @PostMapping("/listSetmeal")
    public ResponseObject listSetmeal(@RequestBody Users users) {
        //调用service层方法获取套餐列表
        return setmealService.listSetmeal(users.getSex());
    }



    @RequestMapping("/loadSetmeal")
    public ResponseObject loadSetmeal(@RequestBody Setmeal setmeal){
        return setmealService.loadSetmeal(setmeal);
    }

    @PostMapping("/getSetmealNameBySmId")
    public ResponseObject getSetmealNameBySmId(@RequestBody Setmeal setmeal) {
        System.out.println("前端传来smId"+setmeal.getSmId());

        //调用service层方法获取套餐名称
        return setmealService.getSetmealNameBySmId(setmeal.getSmId());
    }
}
