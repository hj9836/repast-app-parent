package com.huifangyuan.app.fallback;


import com.huifangyuan.app.cutom.ShopInfoCutom;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

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
            public String selectToken(String token) {
                return null;
            }

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
            public List<MemberProduct> getProductInfoByIntegral(Integer pageNum,Integer pageSize) {
                System.out.println("查询支持用积分兑换的商品信息");
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
            public List<CommentVo> getShopCommentById(Long shopId) {
                System.out.println("根据店铺id获取店铺所有评价，熔断数据");
                return null;
            }

            @Override
            public List<CommentVo> getProductCommentById(Long productId) {
                System.out.println("根据产品id获取产品所有评价，熔断数据");
                return null;
            }

            @Override
            public List<Product> homeSeekProduct(String keywords) {
                System.out.println("首页的搜索，熔断数据");
                return null;
            }

            /**
             * 根据店铺的ID查询店铺的所有信息
             * Mrlin119
             *
             * @param shopId
             * @return
             */
            @Override
            public ShopInfoVo getShopAllInfoByShopId(Long shopId) {
                return null;
            }


            @Override
            public List<MemberProduct> getAllProductByShopId(Long shopId) {
                System.out.println("测试商品信息熔断数据");
                return null;
            }

            @Override
            public List<MemberProduct> getAllShopRecommendProductByShopId(Long shopId) {
                System.out.println("测试店铺推荐商品信息熔断数据");
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

            /**
             * @param shopId
             * @ClassName RedisController
             * @Description
             * @Date 21:03 2019/11/26
             * @author eri c
             * 根据店铺id查询商品类目信息从redis中
             * @Param
             * @Return
             */
            @Override
            public List<ProductCat> selectProductCatByshopIdToRedis(Long shopId) {
                return null;
            }

            /**
             * @param shopId
             * @ClassName RedisController
             * @Description
             * @Date 21:03 2019/11/26
             * @author eri c
             * 根据店铺id查询商品类目信息从redis中
             * @Param
             * @Return
             */
            @Override
            public List<ProductCat> selectShopMenuByShopIdToRedis(Long shopId) {
                System.out.println("根据店铺id查询商品类目信息从redis中");
                return null;
            }


            @Override
            public boolean insertZeroCatToRedis() {
                return false;
            }


            @Override
            public List<ProductCat> getZeroCat() {
                System.out.println("进入"+"获取level=0的分类信息"+"熔断方法");
                return null;
            }

            @Override
            public boolean insertAllShopInfoToRedis() {
                System.out.println("进入"+"向redis中存入全部的商家信息"+"熔断方法");
                return false;
            }

            @Override
            public List<MemberProduct> getRecommandProduct(Integer pageNum, Integer pageSize) {
                System.out.println("进入"+"首页推荐商品（带分页信息）"+"熔断方法");
                return null;
            }

            @Override
            public MemberProduct getProductInfoByPrimaryKey(Long productId) {
                System.out.println("进入"+"根据主键查询商品信息"+"熔断方法");
                return null;
            }
            @Override
            public List<AdvertiseVo> getAdvertise() {
                System.out.println("进入"+"首页广告推荐"+"熔断方法");
                return null;
            }

            @Override
            public List<CanTeenDateVo> getCanteenDateByShopId(Long ShopId) {
                System.out.println("进入"+"全组最屌的店家+商品"+"熔断方法");
                return null;
            }

            @Override
            public ShopInfoCutom getShopAllInfoLAOYANG(Long ShopId) {
                System.out.println("进入"+"店家信息+小图标"+"熔断方法");
                return null;
            }

            @Override
            public boolean insertAllShopMenuAndProductToRedis() {
                System.out.println("进入"+"向redis中插入全部商家的菜单和商品信息"+"熔断方法");
                return false;
            }


        };
        return repastService;
    }
}
