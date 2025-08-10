package com.example.jnu_tijian.exception;

public class TijianAppException extends RuntimeException{

    private Integer code;     //status
    private String message;   //desc

    public TijianAppException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public TijianAppException() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}