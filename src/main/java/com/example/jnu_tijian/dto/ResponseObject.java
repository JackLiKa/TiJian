package com.example.jnu_tijian.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 统一后端返回给前端的数据类型
 *
 */
public class ResponseObject<T> {

    //信号
    private Integer status;

    //提示信息
    private String desc;

    //前端想要的结果,比如，所以需要定义成泛型： Poduct  ProductList   Cart  CartList    Users   Doctor
    T data;

    public String toJSONString(){
        String jsonStr=null;
        try{
            jsonStr=new ObjectMapper().writeValueAsString(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }

    //通用操作：成功
    public static final Integer SUCCESS_STATUS=200;
    public static final String SUCCESS_DESC="成功";



    //失败需要明确失败的原因，所以这里不定义通用操作



    public ResponseObject(Integer status, String desc, T data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

    public ResponseObject(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
