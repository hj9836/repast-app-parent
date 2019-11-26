package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.vo.MemberProduct;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ProductCatMapper extends Mapper<ProductCat> {

    /**
     * 根据店铺id获取商品类目信息
     */
    List<ProductCat> selectProductByshopId(Long shopId);

    List<Long>  selectProductCatByShopId(Long ShopId);

    List<ProductCat> selectAllProductCat();

    List<ProductCat> getCateByLevel();
}
