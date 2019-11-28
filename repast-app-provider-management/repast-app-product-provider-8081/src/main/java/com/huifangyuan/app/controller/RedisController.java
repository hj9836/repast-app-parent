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
    private ShopmenuRedisService shopmenuRedisService;

    @Autowired
    private ProductCatService productCatService;

    @Autowired
    MyRedisService myRedisService;

    @Autowired
    RedisService redisService;

    @Autowired
    ProductInfoService productInfoService;

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
     * 根据店铺id获取所有商品类目信息 存入redis
     *@Date 23:23 2019/11/26
     *@author eric
     *@Param
     *@Return
     **/
    @PostMapping ("/insertAllProductCatToRedis")
    public boolean insertAllProductCatToRedis(){
        return shopmenuRedisService.insertAllProductCatToRedis(redisService);
    }
    /**
     *@ClassName RedisController
     *@Description
     * 根据店铺id获取所有商品类目信息 从redis中
     *@Date 23:23 2019/11/26
     *@author eric
     *@Param
     *@Return
     **/
    @GetMapping ("/selectProductByshopIdToRedis")
    public List<ProductCat> selectProductCatByshopIdToRedis(@RequestParam(value = "shopId") Long shopId){

        return productCatService.selectProductCatByshopIdToRedis(shopId,redisService,shopmenuRedisService);
    }

    @GetMapping ("/insertZeroCatToRedis")
    public boolean insertZeroCatToRedis(){


        return productInfoService.insertZeroCatToRedis(redisService,myRedisService);
    }

    @GetMapping("/getZeroCat")
    public List<ProductCat> getZeroCat(RedisService redisService){

        return productCatService.getZeroCat(redisService);
    }

    @PostMapping ("/insertAllShopInfoToRedis")
    public boolean insertAllShopInfoToRedis(){

        return myRedisService.insertAllShopInfoToRedis(redisService);
    }

    @PostMapping("/getRecommandProduct")
    public List<MemberProduct> getRecommandProduct(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){



        return productInfoService.getRecommandProduct(pageNum,pageSize,redisService,myRedisService);
    }
}
