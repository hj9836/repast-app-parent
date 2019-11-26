package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ShopInfoVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


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





}
