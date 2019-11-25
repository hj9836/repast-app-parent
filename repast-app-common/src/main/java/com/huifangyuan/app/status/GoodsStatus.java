package com.huifangyuan.app.status;

public enum GoodsStatus {

    GET_SUCCESS("200","成功获取商品"),
    GET_FAILED("400","获取商品失败");

    private String code;
    private String msg;

    GoodsStatus(String code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
