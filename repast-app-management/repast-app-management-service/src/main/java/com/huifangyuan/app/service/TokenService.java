package com.huifangyuan.app.service;

import com.huifangyuan.app.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;
    /**
     * 验证用户是否登陆
     * @param token
     * @return
     */
    public String selectToken(String token){
        String token1 = tokenMapper.selectToken(token);
        if (null != token1){
            return token1;
        }
        return null;
    }
}
