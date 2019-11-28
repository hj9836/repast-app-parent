package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.Comment;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.service.*;
import com.huifangyuan.app.vo.AdvertiseVo;
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

    @Autowired
    private ShopCommentService shopCommentService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MyRedisService myRedisService;

    @Autowired
    private ShopInfoMapper shopInfoMapper;


    @GetMapping("/getShopInfoByPrimaryKey")
    public ShopInfoVo getShopInfoByPrimaryKey(@RequestParam(value = "ShopId") Long ShopId){

        System.out.println("进入8081");
        System.out.println(ShopId);
        return shopInfoService.getShopInfoByPrimaryKey(ShopId,redisService,myRedisService);
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

    /**
     * 通过店铺id查询出店铺评价
     * @param shopId
     * @return
     */
    @GetMapping("/getShopCommentById")
    public List<Comment> getShopCommentById(@RequestParam("shopId") Long shopId){
        Comment comment = new Comment().setShopId(shopId);
        try {
            List<Comment> comments = shopCommentService.selectDomain(comment);
            if (comments.size() > 0){
                    return comments;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过店铺ID查询店铺的所有的信息
     * Mrlin119
     * @param shopId
     * @return
     */
    @GetMapping("/getShopAllInfo")
    public ShopInfoVo getShopAllInfoByShopId(Long shopId){
        return shopInfoService.getShopAllInfoByShopId(shopId);
    }

    public List<AdvertiseVo> getAdvertise(){

        return shopInfoMapper.getAdvertise();
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
    public List<Product> getProductByShopId(@RequestParam("shopId") Long shopId) {
        return productService.getProductByShopId(shopId);
    }
}


