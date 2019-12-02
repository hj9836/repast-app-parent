package com.huifangyuan.app.service;

import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        int size = 0;
        try {

            productCatList = productCatMapper.selectAllProductCat();
            System.out.println(productCatList.get(0).getShopId());
            size = productCatList.size();//这步可能出现空指针异常，故trycatch
            System.out.println("查看mysql数据长度"+size);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



        //2、如果查询到的mysql商品不为空，则存入redis
        if (0!=size || null!= productCatList){
            System.out.println("进入不为空判断方法体内，准备执行for循环");
            List<ProductCat> nowProductCats = new ArrayList<ProductCat>();

            //循环存入redis
            Long shopId = 0l;
            String setstatus = "";

            for(int i =0;i<size;i++){
                System.out.println("进入for循环内");
               /* ————————————————————————————————————————新增逻辑————————————————————————————————————-*/
                Long nowShopId = productCatList.get(i).getShopId();
                if (i==0)shopId=nowShopId;//第一次插入时的判断

                if (nowShopId==shopId){
                    nowProductCats.add(productCatList.get(i));//放入容器中
                    if (i==size-1){//最后一条插入时的判断：
                        //然后存入redis
                        System.out.println("最后一次插入："+nowProductCats);
                        String s = JSONUtil.toJsonString(nowProductCats);
                        setstatus = redisService.set(REDIS_SHOPMENU_KEY + shopId, s);
                        System.out.println("redis中的key值为："+REDIS_SHOPMENU_KEY + shopId);
                        System.out.println("存入redis是否成功"+setstatus);
                        //存入完毕
                    }

                }else {//如果不等于上一条数据的shopId，则新创建容器List，！！并且，把上一个List容器的信息存入redis!!

                    //然后存入redis
                    System.out.println("非最后一次的插入"+nowProductCats);
                    String s = JSONUtil.toJsonString(nowProductCats);
                    setstatus = redisService.set(REDIS_SHOPMENU_KEY + shopId, s);
                    System.out.println("redis中的key值为："+REDIS_SHOPMENU_KEY + shopId);
                    System.out.println("存入redis是否成功"+setstatus);
                    //存入完毕
                    shopId = nowShopId;//覆盖shopId
                    nowProductCats = new ArrayList<ProductCat>();//覆盖新的List容器
                    nowProductCats.add(productCatList.get(i));//放入容器中

                }
                shopId = productCatList.get(i).getShopId();//获取当前准备插入redis中条数的shopId



                /* ————————————————————————————————————————新增逻辑————————————————————————————————————-*/





                //把mysql查询到的实体类转换成JSON
                String productJSON = JSONUtil.toJsonString(productCatList.get(i));





            }
            return true;
        }
        return false;
    }







    public List<ProductCat> getShopMenuByShopIdFromRedis (Long shopId, RedisService redisService){
        String productCat_JSON = null;
        try {
            String s = redisService.get(REDIS_SHOPMENU_KEY + shopId);
            if (null!=s && ""!=s){//杀空
               return JSONUtil.toList(s, ProductCat.class);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
