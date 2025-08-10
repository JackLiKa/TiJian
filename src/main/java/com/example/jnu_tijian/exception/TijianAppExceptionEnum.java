package com.example.jnu_tijian.exception;

/**
 * 软件使用手册
 */
public enum TijianAppExceptionEnum {

    PHONE_NOT_REGISTER(1000,"该手机号尚未注册"),
    PHONE_OR_PASSWORD_IS_WRONG(1001,"手机号或密码错误，请检查账号或者密码"),
    SYSTEM_ERROR(1002,"系统异常"),
    ALREADY_APPOINTMENT_CURRENTDAY(1003,"您当天已有预约"),
    USER_LOGIN_TIMEOUT(1004,"用户登录已超时"),
    NOT_LOGIN(1005,"请先登录"),
    NO_HOSPITAL(1006,"没有可预约的医院"),
    EXISTING_ORDER(1007,"您当天已有预约，请勿重复预约"),
    LOAD_MEAL_ERROR(1008,"套餐失败，请稍后再试"),
    REGISTER_FAILED(1009,"注册失败，请稍后再试"),
    PAY_FAILED(1010,"支付失败，请稍后再试"),
    ORDER_NOT_FOUND(1011,"订单不存在，请检查订单号是否正确"),
    INIT_CONFIG_FAILED(1012,"初始化AliPay配置失败，请稍后再试"),
    ISPAYED(1013,"订单已支付，请勿重复支付"),
    ;



    private TijianAppExceptionEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}