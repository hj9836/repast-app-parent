package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoService extends BaseService<Product> {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
        通用mapper方法
     **/
    public Mapper<Product> getMapper() {

        return productInfoMapper;
    }
    /**
     * @description
     *      通过店铺主键查询商品列表
     * @param [shopId]
     * @date 2019/11/21
     * @return java.util.List<com.aaa.lee.app.domain.Product>
     * @throws
     **/
    public List<Product> getProductByShopId(Long shopId) {
        List<Product> productList = productInfoMapper.getProductByShopId(shopId);
        if(productList.size() > 0) {
            return productList;
        }
        return null;
    }
    /**
     *@ClassName ProductService
     *@Description
     *@Date 10:05 2019/11/22
     *  根据商品类目id获取商品信息列表并按照销量sale排序
     *@author eric
     *@Param
     *@Return
     **/
    public  List<Product> getProductByCategoryId(Long productCategoryId){
        List<Product> productList = productInfoMapper.getProductByCategoryId(productCategoryId);
        if (productList.size()>0){
            return  productList;
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
    public List<MemberProduct> selectProductByShopId(Long shopId){
        List<MemberProduct> memberProductList = productInfoMapper.selectProductByShopId(shopId);
        if (memberProductList.size()>0){
            return memberProductList;
        }
        return null;
    }


















}
