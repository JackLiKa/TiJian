package com.example.jnu_tijian.handler;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.exception.TijianAppException;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    //只处理业务异常
    @ExceptionHandler(TijianAppException.class)
    @ResponseBody  //返回json格式数据给前端
    public ResponseObject handleTijianAppException(TijianAppException e){
        e.printStackTrace();
        System.out.println("异常信息："+e.getStackTrace());

        return new ResponseObject(e.getCode(),e.getMessage());

    }

    //处理所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody  //返回json格式数据给前端
    public ResponseObject handleAllException(Exception e){
        e.printStackTrace();
        System.out.println("异常信息："+e.getStackTrace()+",message:"+e.getMessage());
        return new ResponseObject(TijianAppExceptionEnum.SYSTEM_ERROR.getCode(),TijianAppExceptionEnum.SYSTEM_ERROR.getMessage());

    }

}
