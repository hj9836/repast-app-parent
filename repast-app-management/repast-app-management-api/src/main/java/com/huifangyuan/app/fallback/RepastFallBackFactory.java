package com.huifangyuan.app.fallback;


import com.huifangyuan.app.domain.Comment;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.CategoryShop;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ProductVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/20 11:41
 * @Description
 **/
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {

    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService() {

            @Override
            public ShopInfoVo getShopInfoByPrimaryKey(Long ShopId) {
                System.out.println("进入根据主键查询商铺信息熔断方法");
                return null;
            }

            @Override
            public List<ProductCat> getCateByLevel() {
                System.out.println("通过层级查询商品类型");
                return null;
            }

            @Override
            public List<ProductCat> getCategoryByShopId(Long shopId) {
                System.out.println("根据商品类目信息获取商品信息熔断数据");
                return null;
            }

            @Override
            public List<Product> getProductByShopId(Long shopId) {
                System.out.println("根据店铺id查询商品信息熔断数据");
                return null;
            }

            @Override
            public List<Product> getProductByProductCategoryId(Long productCategoryId) {
                System.out.println("根据商品类目id测试商品信息熔断数据");
                return null;
            }

            @Override
            public List<MemberProduct> selectAllProduct(Long shopId) {
                System.out.println("测试获取商品熔断数据");
                return null;
            }

            @Override
            public List<CategoryShop> getCategoryShop(Long parentId) {
                System.out.println("测试根据商品类目查询店铺信息，熔断数据");
                return null;
            }

            @Override
            public List<Product> selectShopProduct(ProductVo productVo) {
                System.out.println("测试通过店铺 关键字搜素商品列表，熔断数据");
                return null;
            }

            @Override
            public List<Product> selectIntegralProduct() {
                System.out.println("测试进入积分商城，查询所有积分商品列表，熔断数据");
                return null;
            }

            @Override
            public List<Comment> getShopCommentById(Long shopId) {
                System.out.println("根据店铺id获取店铺所有评价，熔断数据");
                return null;
            }

            @Override
            public List<Product> homeSeekProduct(String keywords) {
                System.out.println("首页的搜索，熔断数据");
                return null;
            }

            @Override
            public boolean insertAllProductToRedis() {

                return false;
            }

            @Override
            public List<MemberProduct> test(Long ShopId) {
                return null;
            }

            @Override
            public boolean insertAllProductCatToRedis() {
                System.out.println("查询所有商品类目信息 存入redis");
                return false;
            }

            @Override
            public List<ProductCat> selectProductCatByshopIdToRedis(Long shopId) {
                System.out.println("根据店铺id查询商品类目信息从redis中");
                return null;
            }

            @Override
            public boolean insertZeroCatToRedis() {
                return false;
            }
        };
        return repastService;
    }
}
