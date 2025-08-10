package com.example.jnu_tijian.service;


import com.example.jnu_tijian.dto.ResponseObject;

import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.exception.TijianAppException;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UsersMapper usersMapper;

    public ResponseObject userLogin(Users user) {
        //doctorMapper.selectByPrimaryKey()
        Users result= usersMapper.login(user);
        System.out.println("userLoginservice:"+result.toString());
        if(result==null){
            //抛出异常
            throw new TijianAppException(TijianAppExceptionEnum.PHONE_OR_PASSWORD_IS_WRONG.getCode(),
                    TijianAppExceptionEnum.PHONE_OR_PASSWORD_IS_WRONG.getMessage());
        }else{
            System.out.println("登录成功: " + result);
            return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,result);
        }
    }
    public ResponseObject userRegister(Users user) {
        return usersMapper.register(user)>0
                ? new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC)
                : new ResponseObject(TijianAppExceptionEnum.REGISTER_FAILED.getCode(),
                TijianAppExceptionEnum.REGISTER_FAILED.getMessage());
    }
}
