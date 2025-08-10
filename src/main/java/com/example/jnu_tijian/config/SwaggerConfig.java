package com.example.jnu_tijian.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("演示项目API") // 标题
                        .description("演示项目") // 描述
                        .version("1.0")); // 版本
    }
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .openapi("3.0.0") // OpenAPI版本
//                .info(new Info()
//                        .title("API") // 标题
//                        .description("swagger") // 描述
//                        .version("3.0.0")); // 版本
//    }

}