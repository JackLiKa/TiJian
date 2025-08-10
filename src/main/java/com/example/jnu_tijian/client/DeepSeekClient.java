package com.example.jnu_tijian.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * DeepSeek API客户端，用于调用DeepSeek的文本生成接口。
 * 通过配置的API地址和密钥，提供与DeepSeek服务交互的功能。
 */
@Component
public class DeepSeekClient {

//    @Value("${deepSeek.api.url}")
    private static String apiUrl="https://api.deepseek.com/beta";

//    @Value("${deepSeek.api.key}")
    private static String apiKey="sk-550c30e37bf3466d8daeced5914b267d";

    /**
     * 向DeepSeek的completions接口发送POST请求，生成文本响应。
     *
     * @param prompt 用户输入的提示文本
     * @return API返回的响应内容字符串
     * @throws IOException 网络请求或响应处理异常
     */
    public String callDeepSeek(String prompt) throws IOException {
        String endpoint = this.apiUrl + "/completions"; // OpenAI 兼容的 completions 接口

        // 构建请求体，包含模型、提示、最大token数和温度参数
        String requestBody = String.format("{\"model\": \"deepseek-chat\"," +
                        " \"prompt\": \"%s\"," +
                        " \"max_tokens\": 2048," +
                        " \"temperature\": 0.35}",
                prompt
        );

        // 创建POST请求对象，设置请求头和请求体
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + this.apiKey);
        httpPost.setEntity(new StringEntity(requestBody,"UTF-8"));

        // 执行HTTP请求并处理响应，确保资源正确关闭
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
    }
}
