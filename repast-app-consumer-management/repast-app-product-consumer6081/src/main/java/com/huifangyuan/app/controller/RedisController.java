package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/insertAllProductCatToRedis")
    @ApiOperation(value = "将全部商品类目存入redis",notes = "key为静态常量+shop_id，value为该商品类目的全部数据")
    public ResultData insertAllProductCatToRedis(){
        if (true==repastService.insertAllProductCatToRedis()){
            return success();
        }
        return failed();
    }
    @GetMapping("/selectProductCatToRedis")
    @ApiOperation(value = "商品类目",notes = "根据店铺id获取所有的商品类目信息")
    public ResultData selectProductCatToRedis(Long shopId){
        List<ProductCat> productCatList = repastService.selectProductCatToRedis(shopId);
        if (null !=productCatList && productCatList.size()>0){
            return success(productCatList);
        }
        return failed();
    }

    @GetMapping("/insertZeroCatToRedis")
    @ApiOperation(value = "把0级分类存入redis",notes = "把level=0的分类存入redis中，key值为Zerocat")
    public ResultData insertZeroCatToRedis(/*@RequestParam("shopId") Long ShopId*/){


        if (false!=repastService.insertZeroCatToRedis()){

            return success(repastService.insertZeroCatToRedis());
        }
        return failed();
    }



}
