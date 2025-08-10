package com.example.jnu_tijian.interceptor;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;



public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入 JWT拦截器。。。。。。。。。。");
        //解析
        String token = request.getHeader("token");

        if (token != null) {
            System.out.println("开始解析JWT。。。。。。。。。。。。。。。");
            try {
                Claims claims = Jwts.parser().setSigningKey("1302691900").parseClaimsJws(token).getBody();  //获取数据;

                Users user = new ObjectMapper().readValue((String) claims.get("currentUser"), Users.class);
                request.setAttribute("user", user);
            } catch (Exception e) {
                //捕获过期异常
                e.printStackTrace();
                System.out.println("异常信息JWT拦截器输出：" + e.getStackTrace());
                //返回一个json格式的ResponseObject
                ResponseObject apiResponse = new ResponseObject<>(TijianAppExceptionEnum.USER_LOGIN_TIMEOUT.getCode(), TijianAppExceptionEnum.USER_LOGIN_TIMEOUT.getMessage());

                response.getWriter().println(apiResponse.toJSONString());
                return false;
            }
        }
        return true;
    }
}
