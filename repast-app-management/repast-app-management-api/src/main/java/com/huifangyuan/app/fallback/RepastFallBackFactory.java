package com.huifangyuan.app.fallback;


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
        };
        return repastService;
    }
}
