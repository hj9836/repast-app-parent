package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.mapper.ProductMapper;
import com.huifangyuan.app.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:57
 * @Description
 **/
@Service
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mapper<Product> getMapper() {
        return productMapper;
    }

    /**
     * @author Seven Lee
     * @description
     *      通过店铺主键查询商品列表
     * @param shopId
     * @date 2019/11/21
     * @return java.util.List<com.aaa.lee.app.domain.Product>
     * @throws 
    **/
    public List<Product> getProductByShopId(Long shopId) {
        List<Product> productList = productMapper.getProductByShopId(shopId);
        if(productList.size() > 0) {
            return productList;
        }
        return null;
    }

    /**
     * 通过店铺 关键字搜素商品列表
     * @param productVo
     * @return
     */
    public List<Product> selectShopProduct(ProductVo productVo){
        if(null!=productVo.getName()){
            productVo.setName("%"+productVo.getName()+"%");
        } else {
            productVo.setName("%%");
        }
        List<Product> products = productMapper.selectShopProduct(productVo);
        if(products.size()>0){
            return products;
        }
        return null;
    }

    /**
     * 进入积分商城，查询所有积分商品列表
     * @return
     */
    public List<Product> getIntegralProduct(){
        List<Product> products = productMapper.getIntegralProduct();
        if(products.size()>0){
            return products;
        }
        return null;
    }

}
