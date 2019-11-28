package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.cutom.ShopInfoCutom;
import com.huifangyuan.app.domain.Comment;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.ProductVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 10:55
 * @Description
 *      商家controller
 **/
@RestController
@Api(value = "商家信息", tags = "商家信息接口")
public class ShopInfoController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @GetMapping("/getShopInfoByPrimaryKey")
    @ApiOperation(value = "商家信息",notes = "根据主键获取商家信息")
    public ResultData getShopInfoByPrimaryKey(@RequestParam("shopId") Long ShopId){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        if (null!=repastService.getShopInfoByPrimaryKey(ShopId)){
            System.out.println(repastService.getShopInfoByPrimaryKey(ShopId).getName());
            dataMap.put("Shop",repastService.getShopInfoByPrimaryKey(ShopId));
            return success(repastService.getShopInfoByPrimaryKey(ShopId));
        }
        return failed();
    }

    /**
     * @Author 闫增健
     * 通过店铺 关键字搜素商品列表
     * @param productVo
     * @return
     */
    @PostMapping("/selectShopProduct")
    @ApiOperation(value = "商品信息", notes = "通过店铺搜素查询商品列表")
    public ResultData selectShopProduct(ProductVo productVo){
        List<Product> products = repastService.selectShopProduct(productVo);
        if (null != products){
            return success("搜素成功",products);
        }else {
            return failed();
        }
    }
    /**
     * @Author 闫增健
     * 进入积分商城，查询所有积分商品列表
     */
    @GetMapping("/selectIntegralProduct")
    @ApiOperation(value = "积分商品列表", notes = "进入积分商城，查询所有积分商品列表")
    public ResultData selectIntegralProduct(){
        List<Product> products = repastService.selectIntegralProduct();
        if (null != products){
            return success("查询成功",products);
        }
        return failed();
    }

    /**
     * 通过商家店铺id查询店铺评价
     * @param shopId
     * @return
     */
    @GetMapping("/getShopCommentById")
    @ApiOperation(value = "查询店铺评价" , notes = "通过主键查询商家全部评价" )
    public ResultData getShopCommentById(Long shopId){
        List<Comment> shopCommentById = repastService.getShopCommentById(shopId);
        if (null != shopCommentById){
            return success(shopCommentById);
        }else {
            return failed();
        }
    }
    /**
     * 通过店铺的ID查询到当前店铺的详细信息
     * <>霍晨亮</>
     * @param shopId
     * @return
     */
    @GetMapping("/getShopAllInfo")
    @ApiOperation(value = "店铺的详细信息",notes = "通过店铺的ID查询到当前店铺的详细信息")
    public ResultData getShopAllInfoByShop(Long shopId){
        ShopInfoVo shopAllInfoByShop = repastService.getShopAllInfoByShopId(shopId);
        if (null != shopAllInfoByShop){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("shop",shopAllInfoByShop);
            return success(map);
        }
        return null;
    }
    /**
     * @description
     *      通过店铺主键查询商品列表
     * @param [shopId]
     * @date 2019/11/21
     * @return java.util.List<com.aaa.lee.app.domain.Product>
     * @throws
     **/
    @GetMapping("/getProductByShopId")
    @ApiOperation(value = "商品信息", notes = "通过店铺主键查询商品列表")
    public ResultData getProductByShopId(Long shopId) {
        List<Product> productList = repastService.getProductByShopId(shopId);
        if(null != productList) {
            return success(productList);
        } else {
            return failed();
        }
    }


    @GetMapping("/getShopAllInfoLAOYANG")
    @ApiOperation(value = "真正的店铺的详细信息，一对多",notes = "一个店铺信息，四个图标信息")
    public ResultData getShopAllInfoLAOYANG(Long shopId){
        ShopInfoCutom shopAllInfoByShop = repastService.getShopAllInfoLAOYANG(shopId);
        if (null != shopAllInfoByShop){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("shop",shopAllInfoByShop);
            return success(map);
        }
        return null;
    }

}
