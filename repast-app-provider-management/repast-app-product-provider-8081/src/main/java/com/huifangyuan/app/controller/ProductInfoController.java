package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.*;
import com.huifangyuan.app.vo.CommentVo;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCatService productCatService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MyRedisService myRedisService;
    @Autowired
    private ShopCommentService shopCommentService;


    /**
     *@ClassName ProductController
     *@Description
     *@Date 19:42 2019/11/21
     *@author eric
     * 根据店铺的id获取所有的商品类目
     *@Param
     *@Return
     **/
    @GetMapping("/getCatByShopId")
    public List<ProductCat> getCategoryByShopId(@RequestParam("shopId") Long shopId) {
        try {
            List<ProductCat> catList = productCatService.selectProductByshopid(shopId);
            if(catList.size() > 0) {
                return catList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@ClassName ProductController
     *@Description
     *@Date 10:28 2019/11/22
     * 根据商品类目id获取商品信息并根据销量sale排序
     *@author eric
     *@Param
     *@Return
     **/
    @GetMapping("/getProductByProductCategoryId")
    public List<Product> getProductByProductCategoryId(@RequestParam("productCategoryId") Long productCategoryId){
        Product product = new Product().setProductCategoryId(productCategoryId);
        try {
            List<Product> productList = productInfoService.selectDomain(product);
            if (productList.size()>0){
                return productList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**@ClassName ProductService
     *@Description
     *@Date 16:47 2019/11/23
     *@author eric
     *      根据商铺id获取所有商品信息 并判断促销类型 展示对应促销类型价格
     *@Param
     *@Return
     **/

    @GetMapping("/selectAllMemberProduct")
    public List<MemberProduct> selectAllProduct(@RequestParam("shopId") Long shopId){
        List<MemberProduct> memberProductList = productInfoService.selectProductByShopId(shopId);
        if (memberProductList.size()>0){
            return memberProductList;
        }
        return null;
    }

    /**
     * 根据店铺ID查询所有商品部分信息
     * <>霍晨亮</>
     * @param shopId
     * @return
     */
    @GetMapping("/getAllProduct")
    public List<MemberProduct> getAllProductByShopId(Long shopId){
        List<MemberProduct> allProductByShopId = productInfoService.getAllProductByShopId(shopId,redisService,myRedisService);
        if (allProductByShopId.size()>0){
            return allProductByShopId;
        }
        return null;
    }

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品部分信息
     * <>霍晨亮</>
     * @param shopId
     * @return
     */
    @GetMapping("/getAllShopRecommendProductByShopId")
    public List<MemberProduct> getAllShopRecommendProductByShopId(Long shopId) {
        List<MemberProduct> allShopRecommendProductByShopId = productInfoService.getAllShopRecommendProductByShopId(shopId,redisService,myRedisService);
        if (allShopRecommendProductByShopId.size()>0){
            return allShopRecommendProductByShopId;
        }
        return null;
    }

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品部分信息
     * <>霍晨亮</>
     * @param
     * @return
     */
    @GetMapping("/getProductInfoByPrimaryKey")
    public MemberProduct getProductInfoByPrimaryKey(Long productId) {

        return productInfoService.getProductInfoByPrimaryKey(productId,redisService,myRedisService);
    }

    /**
     * 通过产品id查询产品评价
     * @param
     * @return
     */

    @GetMapping("/getProductCommentById")
    public List<CommentVo> getProductCommentById(@RequestParam("productId") Long productId){
        List<CommentVo> productCommentById = shopCommentService.getProductCommentById(productId);
        if (null != productCommentById){
            return productCommentById;
        }
        return null;
    };


}
