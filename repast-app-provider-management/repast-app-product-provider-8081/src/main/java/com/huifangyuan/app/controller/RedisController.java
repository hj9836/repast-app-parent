package com.huifangyuan.app.controller;

import com.huifangyuan.app.service.MyRedisService;
import com.huifangyuan.app.service.ProductInfoService;
import com.huifangyuan.app.service.RedisService;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:00
 * @Description
 **/
@RestController
public class RedisController {

    @Autowired
    private MyRedisService myRedisService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProductInfoService productInfoService;

    @PostMapping ("/insertAllProductToRedis")
    public boolean insertAllProductToRedis(){


        return myRedisService.insertAllProductToRedis(redisService);
    }

    @GetMapping ("/test")
    public List<MemberProduct> test(@RequestParam(value = "ShopId") Long ShopId){


        return productInfoService.test(ShopId,redisService,myRedisService);
    }

    @GetMapping ("/insertZeroCatToRedis")
    public boolean insertZeroCatToRedis(){


        return myRedisService.insertZeroCatToRedis(redisService,myRedisService);
    }

}
