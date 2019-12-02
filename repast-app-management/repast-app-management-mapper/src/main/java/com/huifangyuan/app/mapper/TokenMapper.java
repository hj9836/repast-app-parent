package com.huifangyuan.app.mapper;

public interface TokenMapper{
    /**
     * 验证用户是否登陆
     * @param token
     * @return
     */
    String selectToken(String token);
}
