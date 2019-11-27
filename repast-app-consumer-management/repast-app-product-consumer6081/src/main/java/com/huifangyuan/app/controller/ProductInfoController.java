package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.MemberProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 10:55
 * @Description
 *      商家controller
 **/
@RestController
@Api(value = "商品信息", tags = "商品信息接口")
public class ProductInfoController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     *@ClassName ProductController
     *@Description
     *@Date 19:31 2019/11/21
     * 根据店铺id获取所有商品类目信息
     *@author eric
     *@Param
     *@Return
     **/
    @GetMapping("/getCatByShopId")
    @ApiOperation(value = "商品类目",notes = "根据店铺id获取所有的商品类目信息")
    public ResultData getCategoryByShopId(Long shopId){
        List<ProductCat> catList = repastService.getCategoryByShopId(shopId);
        if (null != catList){
            return success(catList);
        }
        return failed();
    }
    /**
     *@ClassName ProductController
     *@Description
     *@Date 22:28 2019/11/22
     *@author eric
     * 根据商品类目id 获取所有商品信息
     *@Param
     *@Return
     **/
    @GetMapping("/getProductByCategoryId")
    @ApiOperation(value = "商品信息",notes = "根据商品类目信息获取所有商品信息列表")
    public ResultData getProductByCategoryId(Long productCategoryId){
        List<Product> productList = repastService.getProductByProductCategoryId(productCategoryId);
        if (null != productList){
            return success(productList);
        }
        return failed();
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
    @ApiOperation(value = "商品信息",notes = "根据商铺id获取所有商品信息 并判断促销类型 展示对应促销类型价格")
    public ResultData selectAllProduct(@RequestParam("shopId") Long shopId){
        List<MemberProduct> memberProductList = repastService.selectAllProduct(shopId);
        if (memberProductList.size()>0){
            return success(memberProductList);
        }
        return failed();
    }

    /**
     * 根据店铺ID查询所有商品的信息
     * <>霍晨亮</>
     * @param shopId
     * @return
     */
    @GetMapping("/getAllProductByShopId")
    @ApiOperation(value = "商品信息", notes = "通过店铺主键查询商品信息")
    public ResultData getAllProductByShopId(Long shopId){
        List<MemberProduct> allProductByShopId = repastService.getAllProductByShopId(shopId);
        if (null != allProductByShopId){
            return success(allProductByShopId);
        }else{
            return failed();
        }
    }

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品信息
     * <>霍晨亮</>
     * @param shopId
     * @return
     */
    @GetMapping("/getAllShopRecommendProductByShopId")
    @ApiOperation(value = "店铺推荐商品信息", notes = "通过店铺主键查询店铺推荐商品的详细信息")
    public ResultData getAllShopRecommendProductByShopId(Long shopId){
        List<MemberProduct> recommendProductList = repastService.getAllShopRecommendProductByShopId(shopId);
        if (recommendProductList.size()>0){
            return success(recommendProductList);
        }else{
            return failed();
        }
    }



}
