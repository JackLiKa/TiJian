package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.client.DeepSeekClient;
import com.example.jnu_tijian.dto.DeepSeekRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DeepSeekController {

    @Autowired
    private DeepSeekClient deepSeekClient;

    /**
     * 处理POST请求，调用DEEPSEEK AI模型生成医疗咨询回答
     *
     * @param request 包含用户输入提示信息的请求数据传输对象
     * @return DEEPSEEK模型返回的回答内容，或调用失败时的错误信息
     */
    @PostMapping("/askDS")
    public String askDeepSeek(@RequestBody DeepSeekRequest request) {
        String prompt = request.getPrompt();

        // 调用DEEPSEEK API生成回答，构造包含角色设定和用户问题的完整请求内容
        try {
            return deepSeekClient.callDeepSeek("你是DEEPSEEK，是一名医生,你现在需要根据我的一下问题来简单的回答:"+prompt);
        } catch (IOException e) {
            // 捕获API调用异常，记录堆栈信息并返回错误响应
            e.printStackTrace();
            return "Error calling DeepSeek API";
        }
    }
}
