package com.huifangyuan.app.controller;

import com.huifangyuan.app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;
    /**
     * 验证用户是否登陆
     * @param token
     * @return
     */
    @RequestMapping("/token")
    public String selectToken(@RequestParam("token") String token){
        return tokenService.selectToken(token);
    }
}
