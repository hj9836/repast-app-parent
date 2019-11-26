package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 10:55
 * @Description
 *      商家controller
 **/
@RestController
@Api(value = "项目启动时各类信息存入redis的方法集合（TODO最后整合成一个controller）", tags = "项目启动时存入redis")
public class RedisController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @PostMapping("/insertAllProductToRedis")
    @ApiOperation(value = "将全部商品存入redis",notes = "key为静态常量+product_id，value为该商品的全部数据+member_price联查")
    public ResultData insertAllProductToRedis(){


        if (true==repastService.insertAllProductToRedis()){

            return success();
        }
        return failed();
    }


    @GetMapping("/test")
    @ApiOperation(value = "test",notes = "test")
    public ResultData test(@RequestParam("shopId") Long ShopId){


        if (null!=repastService.test(ShopId)){

            return success(repastService.test(ShopId));
        }
        return failed();
    }



}
