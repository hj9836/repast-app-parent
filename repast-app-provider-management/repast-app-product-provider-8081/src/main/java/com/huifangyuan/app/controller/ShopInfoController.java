package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.service.ProductService;
import com.huifangyuan.app.service.ShopInfoService;
import com.huifangyuan.app.vo.ProductVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:00
 * @Description
 **/
@RestController
public class ShopInfoController {

    @Autowired
    ShopInfoService shopInfoService;
    @Autowired
    private ProductService productService;
    @GetMapping("/getShopInfoByPrimaryKey")
    public ShopInfoVo getShopInfoByPrimaryKey(@RequestParam(value = "ShopId") Long ShopId){

        System.out.println("进入8081");
        System.out.println(ShopId);
        return shopInfoService.getShopInfoByPrimaryKey(ShopId);
    }

    /**
     * 通过店铺 关键字搜素商品列表
     * @param productVo
     * @return
     */
    @PostMapping("/selectShopProduct")
    public List<Product> selectShopProduct(@RequestBody ProductVo productVo){
        return productService.selectShopProduct(productVo);
    }

    /**
     * 进入积分商城，查询所有积分商品列表
     */
    @GetMapping("/selectIntegralProduct")
    public List<Product> selectIntegralProduct(){
        return productService.getIntegralProduct();
    }


}
