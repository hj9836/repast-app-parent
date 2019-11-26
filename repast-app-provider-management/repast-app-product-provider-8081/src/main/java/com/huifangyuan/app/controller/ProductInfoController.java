package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.ProductCatService;
import com.huifangyuan.app.service.ProductInfoService;
import com.huifangyuan.app.service.ShopInfoService;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ShopInfoVo;
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
    /*@ClassName ProductService
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


}
