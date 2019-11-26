package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.*;
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
    private ShopmenuRedisService shopmenuRedisService;

    @Autowired
    private ProductCatService productCatService;

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
    /**
     *@ClassName RedisController
     *@Description
     *@Date 21:03 2019/11/26
     *@author eric
     * 根据店铺id查询商品类目信息从redis中
     *@Param
     *@Return
     **/
    @GetMapping("/selectProductCatToRedis")
    public List<ProductCat> selectProductCatToRedis(@RequestParam("shopId") Long shopId){
        return productCatService.selectProductCatByshopIdToRedis(shopId,redisService,shopmenuRedisService);
    }
    /**
     *@ClassName IRepastService
     *@Description
     * 查询所有商品类目信息 存入redis
     *@Date 21:17 2019/11/26
     *@author eric
     *@Param
     *@Return
     **/
    @PostMapping ("/insertAllProductCatToRedis")
    public boolean insertAllProductCatToRedis(){
        return shopmenuRedisService.insertAllProductCatToRedis(redisService);
    }


}
