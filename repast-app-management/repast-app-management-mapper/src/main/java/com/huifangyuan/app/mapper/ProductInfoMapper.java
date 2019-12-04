package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.vo.CanTeenDateVo;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ShopInfoVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface ProductInfoMapper extends Mapper<Product> {

    /**
     * 根据店铺id查询所有商品信息
     * @param shopId
     * @return
     */
    List<Product> getProductByShopId(Long shopId);

    /**
     * 根据类目id查询所有商品
     * @param productCategoryId
     * @return
     */

    List<Product> getProductByCategoryId(Long productCategoryId);

    /**
     * 根据商铺id获取所有商品信息 并判断促销类型 展示对应促销类型价格
     */

    List<MemberProduct> selectProductByShopId(Long shopId);


    List<Long>selectProductIdByShopId(Long ShopId);


    List<MemberProduct> selectAllProductInfo();

    /**
     * 根据店铺ID查询所有商品的信息
     * @param shopId
     * @return
     */
    List<MemberProduct> getAllProductByShopId(Long shopId);

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品信息
     * @param shopId
     * @return
     */
    List<MemberProduct> getAllShopRecommendProductByShopId(Long shopId);

    /**
     * g根据店铺Id查询所有商品的Id
     * @param shopId
     * @return
     */
    List<Long> getAllProductIdByShopId(Long shopId);

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品Id
     * @param shopId
     * @return
     */
    List<Long> getAllShopRecommendProductIdByShopId(Long shopId);


    List<Long> getRecommandProductId(Map<String,Integer> map);

    List<MemberProduct> getProductListByPrimaryKeyList(Map<String,List<Long>> list);

    MemberProduct getProductInfoByPrimaryKey(Long productId);

    List<CanTeenDateVo> getCanteenDateByShopId(Long shopId);

    List<CanTeenDateVo> selectAllShopMenuAndProductToRedis();

}
