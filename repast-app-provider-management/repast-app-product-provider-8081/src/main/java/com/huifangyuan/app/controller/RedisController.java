package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.*;
import com.huifangyuan.app.vo.AdvertiseVo;
import com.huifangyuan.app.vo.CanTeenDateVo;
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
    private MyRedisService myRedisService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ShopInfoService shopInfoService;
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
    @GetMapping ("/selectShopMenuByShopIdToRedis")
    public List<ProductCat> selectShopMenuByShopIdToRedis(@RequestParam(value = "shopId") Long shopId){

        return productCatService.selectShopMenuByShopIdToRedis(shopId,redisService,shopmenuRedisService);
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

    @GetMapping("/getRecommandProduct")
    public List<MemberProduct> getRecommandProduct(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){



        return productInfoService.getRecommandProduct(pageNum,pageSize,redisService,myRedisService);
    }

    @GetMapping ("/getAdvertise")
    public List<AdvertiseVo> getAdvertise(){

        return shopInfoService.getAdvertise();
    }

    @GetMapping ("/getCanteenDateByShopId")
    List<CanTeenDateVo> getCanteenDateByShopId(@RequestParam("shopId") Long ShopId){
        return productInfoService.getCanteenDateByShopId(ShopId,redisService,myRedisService);//想好，如果使用一对多嵌套的实体类Vo，这样就没办法进redis查询了
    }

    @GetMapping ("/insertAllShopMenuAndProductToRedis")
    boolean insertAllShopMenuAndProductToRedis(){
        return myRedisService.insertAllShopMenuAndProductToRedis(redisService);
    }

}
