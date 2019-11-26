package com.huifangyuan.app.service;


import com.huifangyuan.app.domain.Comment;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.fallback.RepastFallBackFactory;
import com.huifangyuan.app.vo.CategoryShop;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ProductVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/20 11:40
 * @Description
 *      当使用feign进行传参的时候，如果是对象,包装类型,实体类...必须要使用@RequestBody，并且这个@RequestBody只能在该方法中出现一次
 *          ResultData selectUsersCondition(@RequestBody User user, @RequestBody UserInfo userInfo);---->错误
 *      当传递的参数是简单类型(String, Integer....8种基本类型+String)，必须要使用@RequestParam("")，这个@RequestPara注解可以出现多个
 *          ResultData selectUsersCondition(@RquestPara("username") String username, @RequestParam("age") Integer age);---->正确
 *
 **/
@FeignClient(value = "product-interface-provider", fallbackFactory = RepastFallBackFactory.class)
public interface IRepastService {

    /*-------------------------------------shopController-----------------------------------------------------*/
    @GetMapping("/getShopInfoByPrimaryKey")
    ShopInfoVo getShopInfoByPrimaryKey(@RequestParam("ShopId") Long ShopId);

    /**
     * 通过层级查询商品类型
     * @param level
     * @return
     */
    @GetMapping("/getByLevel")
    List<ProductCat> getCateByLevel();


    /**
     *
     * @description
     *      通过店铺主键查询商品类目列表
     * @param [shopId]
     * @date 2019/11/21
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    @GetMapping("/getCatByShopId")
    List<ProductCat> getCategoryByShopId(@RequestParam("shopId") Long shopId);

    /**
     *
     * @description
     *      通过店铺主键查询商品列表
     * @param [shopId]
     * @date 2019/11/21
     * @return java.util.List<com.aaa.lee.app.domain.Product>
     * @throws
     **/
    @GetMapping("/getProductByShopId")
    List<Product> getProductByShopId(Long shopId);
    /**
     *@ClassName IRepastService
     *@Description
     * 根据商品类目信息查询商品信息
     *@Date 10:28 2019/11/26
     *@author eric
     *@Param
     *@Return
     **/
    @GetMapping("/getProductByProductCategoryId")
    List<Product> getProductByProductCategoryId(@RequestParam("productCategoryId") Long productCategoryId);
    /*@ClassName ProductService
     *@Description
     *@Date 16:47 2019/11/23
     *@author eric
     *      根据商铺id获取所有商品信息 并判断促销类型 展示对应促销类型价格
     *@Param
     *@Return
     **/

    @GetMapping("/selectAllMemberProduct")
    List<MemberProduct> selectAllProduct(@RequestParam("shopId") Long shopId);

    /*-------------------------------------searchController-----------------------------------------------------*/


    /*---------------------------------------------闫增健-------------------------------------------------------------*/
    /**
     * 根据类目名称 查询店铺列表
     * @param parentId
     * @return
     */
    @GetMapping("/getCategoryShop")
    List<CategoryShop> getCategoryShop(@RequestParam("parentId") Long parentId);
    /**
     * 通过店铺 关键字搜素商品列表
     * @param productVo
     * @return
     */
    @PostMapping("/selectShopProduct")
    List<Product> selectShopProduct(@RequestBody ProductVo productVo);
    /**
     * 进入积分商城，查询所有积分商品列表
     */
    @GetMapping("/selectIntegralProduct")
    List<Product> selectIntegralProduct();


    /**
     * 通过商家店铺id查询店铺评价
     * @param shopId
     * @return
     */
    @GetMapping("/getShopCommentById")
    List<Comment> getShopCommentById(@RequestParam("shopId") Long shopId);

    /**
     * 根据商品name或keywords或product_category_name进行对全部商品的模糊搜索
     * @param keywords
     * @return
     */
    @GetMapping("/homeSeekProduct")
    List<Product> homeSeekProduct(@RequestParam("keywords") String keywords);
    /*---------------------------------------------闫增健-------------------------------------------------------------*/




    /*---------------------------------------------老杨redis-------------------------------------------------------------*/

    @PostMapping("/insertAllProductToRedis")
    boolean insertAllProductToRedis();




    @GetMapping("/test")
    List<MemberProduct> test(@RequestParam("ShopId") Long ShopId);
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
    boolean insertAllProductCatToRedis();

    /**
     *@ClassName RedisController
     *@Description
     *@Date 21:03 2019/11/26
     *@author eric
     * 根据店铺id查询商品类目信息从redis中
     *@Param
     *@Return
     **/
    @GetMapping ("/selectProductByshopIdToRedis")
    List<ProductCat> selectProductCatByshopIdToRedis(@RequestParam(value = "shopId") Long shopId);

    @GetMapping("/insertZeroCatToRedis")
    boolean insertZeroCatToRedis();



    /*---------------------------------------------老杨redis-------------------------------------------------------------*/






}
