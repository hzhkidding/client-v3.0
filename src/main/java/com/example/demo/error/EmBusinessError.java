package com.example.demo.error;

public enum  EmBusinessError implements CommonError{

    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOW_ERROR(10002,"未知错误"),
    LOCATION_ERROR(10003,"未获取手机位置信息，请确保wifi处于开启状态，并开启位置授权"),
    APP_INVOKE_ERROR(10004,"当前状态不稳定，请刷新重试"),
    APP_INSTANCE_ERROR(10005,"当前应用可用资源被占用，请稍后重试"),
    //20000开头为用户相关错误
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"手机或密码不存在"),
    USER_NOT_LOGIN(20003,"用户还未登陆"),

    //30000开头为交易型错误
    STOCK_NOT_ENOUGH(30001,"库存不足")
    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
