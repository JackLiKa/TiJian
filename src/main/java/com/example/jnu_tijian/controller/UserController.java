package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService usersService;

    @PostMapping("/userLogin")
    public ResponseObject login(@RequestBody Users user, HttpServletResponse response) throws JsonProcessingException {
       System.out.println("前端传来数据:"+user.toString());

        ResponseObject responseObject=usersService.userLogin(user);
        Users userObject=(Users)responseObject.getData();
        if(responseObject.getStatus()==ResponseObject.SUCCESS_STATUS){
            Map<String,Object> map=new HashMap<>();
            map.put("currentUser",new ObjectMapper().writeValueAsString(userObject));
            JwtBuilder builder= Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"1302691900")
                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*20))

                    .setClaims(map).setSubject(userObject.getUserId())
                    ;
            String key=builder.compact();
            response.setHeader("token",key);
            System.out.println("返回登录数据给前端："+key.toString());
        return responseObject;
        }

        //压的数据：users
        return responseObject;
    }

    @PostMapping("/userRegister")
    public ResponseObject register(@RequestBody Users user){
        return usersService.userRegister(user);
    }

}
