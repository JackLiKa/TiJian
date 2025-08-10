package com.example.jnu_tijian.interceptor;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入登录拦截器。。。。。。。。。。。。。。。。");

        //看有没有user，没有的话，告诉前端：请先登录   有的话：先比对合法不，合法放行
        Users user = (Users) request.getAttribute("user");
        if (user == null) {
            ResponseObject apiResponse = new ResponseObject<>(TijianAppExceptionEnum.NOT_LOGIN.getCode(), TijianAppExceptionEnum.NOT_LOGIN.getMessage());
            //toJSONString()方法要 手动实现到类中
            response.getWriter().println(apiResponse.toJSONString());
            return false;

        }

        return true;
    }
}