package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.CanTeenDateVo;
import com.huifangyuan.app.vo.MemberProduct;
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
    @GetMapping("/selectShopMenuByShopIdToRedis")
    @ApiOperation(value = "商品类目",notes = "根据店铺id获取所有的商品类目信息")
    public ResultData selectProductCatToRedis(Long shopId){
        List<ProductCat> productCatList = repastService.selectShopMenuByShopIdToRedis(shopId);
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


    @GetMapping("/getZeroCat")
    @ApiOperation(value = "从redis中取出0级分类",notes = "从redis中取出0级分类，如果redis取出不成功再进mysql查询")
    public ResultData getZeroCat(){


        if (null!=repastService.getZeroCat()){

            return success(repastService.getZeroCat());
        }
        return failed();
    }







    @PostMapping("/insertAllShopInfoToRedis")
    @ApiOperation(value = "将全部店铺信息存入redis",notes = "key为静态常量+shop_id，value为该ShioInfoVo类")
    public ResultData insertAllShopInfoToRedis(){


        if (true==repastService.insertAllShopInfoToRedis()){

            return success();
        }
        return failed();
    }

    @GetMapping("/getRecommandProduct")
    @ApiOperation(value = "获取热门商品",notes = "首页页面上的，获取热门商品功能")
    public ResultData getRecommandProduct(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        List<MemberProduct> p = repastService.getRecommandProduct(pageNum,pageSize);

        if (null!=p){

            return success(p);
        }
        return failed();
    }

    @GetMapping("/getAdvertise")
    @ApiOperation(value = "首页广告轮播功能",notes = "首页广告轮播功能，联查sms_advertise和sms_advertise_position")
    public ResultData getAdvertise(){


        if (null!=repastService.getAdvertise()){

            return success(repastService.getAdvertise());
        }
        return failed();
    }

    @GetMapping("/getCanteenDateByShopId")
    @ApiOperation(value = "根据店铺id查询该店铺的一级菜单以及商品信息",notes = "之前写的查询商品信息不能用了，因为跟前端的格式对不上")
    public ResultData getCanteenDateByShopId(@RequestParam("shopId") Long ShopId){
        List<CanTeenDateVo> canteenDateByShopId = repastService.getCanteenDateByShopId(ShopId);


        if (null!=canteenDateByShopId){

            return success(canteenDateByShopId);
        }
        return failed();
    }

    @GetMapping("/insertAllShopMenuAndProductToRedis")
    @ApiOperation(value = "向redis中插入全部店铺的菜单及其商品信息",notes = "向redis中插入全部店铺的菜单及其商品信息，key值为店铺id，格式为CanTeenDateVo")
    public ResultData insertAllShopMenuAndProductToRedis(@RequestParam("shopId") Long ShopId) {
        if (true == repastService.insertAllShopMenuAndProductToRedis()) {

            return success("插入成功");
        }
        return failed();

    }















}
