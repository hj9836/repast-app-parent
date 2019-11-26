package com.huifangyuan.app.mapper;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.vo.ProductVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product> {

    List<Product> getProductByShopId(Long shopId);
    List<Product> selectShopProduct(ProductVo productVo);
    List<Product> getIntegralProduct();

}