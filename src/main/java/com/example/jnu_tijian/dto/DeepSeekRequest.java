package com.example.jnu_tijian.dto;

import lombok.Data;

@Data
public class DeepSeekRequest {
    // 这个不能写错，要跟DeepSeek官方文档一致
    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
