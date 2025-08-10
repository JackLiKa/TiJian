package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class testMybatis {

    @Autowired
    UsersMapper usersMapper;

//    private final UsersMapper usersMapper;
//
//    public testMybatis(UsersMapper usersMapper) {
//        this.usersMapper = usersMapper;
//    }

    @GetMapping("/getAll")
    public List<Users> getAll() {
        List<Users> users = usersMapper.getAll();
        System.out.println(users);
        System.out.println("findAll查找成功");
        return users;
    }
}