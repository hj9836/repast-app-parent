package com.huifangyuan.app.service;

import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import static com.huifangyuan.app.staticstatus.StaticProperties.*;


@Service
public class ShopmenuRedisService {

    @Autowired
    private ProductCatMapper productCatMapper;



    public boolean insertAllProductCatToRedis(RedisService redisService) {

        System.out.println("进入存储方法内");
        //1、首先查询全部的商品类目信息
        List<ProductCat> productCatList = null;
        try {
            productCatList = productCatMapper.selectAllProductCat();
            System.out.println("查看mysql数据长度"+productCatList.size());
        }catch (Exception e){
            e.printStackTrace();
        }



        //2、如果查询到的mysql商品不为空，则存入redis
        if (0!=productCatList.size() || null!= productCatList){
            System.out.println("进入不为空判断方法体内，准备执行for循环");
            //循环存入redis
            for(int i =0;i<productCatList.size();i++){
                System.out.println("进入for循环内");
                //把mysql查询到的实体类转换成JSON
                String productJSON = JSONUtil.toJsonString(productCatList.get(i));
                String setStatu = redisService.set(REDIS_SHOPMENU_KEY + productCatList.get(i).getParentId(), productJSON);
                System.out.println("redis中的key值为："+REDIS_SHOPMENU_KEY + productCatList.get(i).getParentId());
                System.out.println("存入redis是否成功"+setStatu);
            }
            return true;
        }

        return false;
    }







    public List<ProductCat> getProductCatListByPrimayKeyFromRedis (List<Long> list, RedisService redisService){
        String productCat_JSON = null;

        //首先判断list的长度，如果不为空则查询redis
        if(0!=list.size() || null!=list){
            //for循环遍历list数组，用所拿到的Long查询redis中的商品信息
            List<ProductCat> ProductCatList = new ArrayList<ProductCat>();
            try {
            for(int i=0;i<list.size();i++){

                    productCat_JSON = redisService.get(REDIS_SHOPMENU_KEY+list.get(i));//PREFIX_PRODUCT为redis中商品数据的KEY值固定前缀，使用静态常量解决硬编码


                //然后把字符串类型的JSON数据转换成Product实体类对象
                //考虑脏读问题，有可能查询的瞬间商家把该商品删除了，此处要做Redis返回数据的非空判断
                if(""!=productCat_JSON || productCat_JSON!=null){
                    // 如果数据不为空，则添加进Product_List中，否则不作任何处理，直接进入下一次循环
                    ProductCat p = JSONUtil.toObject(productCat_JSON, ProductCat.class);
                    ProductCatList.add(p);
                }

            }
            }catch (Exception e){
                e.printStackTrace();
            }
            //for循环结束之后，返回Product_List
            return ProductCatList;

        }
        //如果list长度为空，则返回msg：没有相关商品！
        return null;


    }

}
