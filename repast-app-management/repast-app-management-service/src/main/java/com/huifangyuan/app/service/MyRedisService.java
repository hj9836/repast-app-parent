package com.huifangyuan.app.service;

import com.huifangyuan.app.cutom.ShopInfoCutom;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.CanTeenDateVo;
import com.huifangyuan.app.vo.MemberProduct;
import com.huifangyuan.app.vo.ShopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import static com.huifangyuan.app.staticstatus.StaticProperties.*;


@Service
public class MyRedisService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductCatMapper productCatMapper;

    @Autowired
    private ShopInfoMapper shopInfoMapper;



    public boolean insertAllProductToRedis(RedisService redisService) {

        System.out.println("进入存储方法内");
        //1、首先查询全部的mysql商品信息
        List<MemberProduct> products_list_mysql = null;
        try {
            products_list_mysql = productInfoMapper.selectAllProductInfo();
            System.out.println("查看mysql数据长度"+products_list_mysql.size());//这步可能出现空指针异常，故trycatch
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



        //2、如果查询到的mysql商品不为空，则存入redis
        if (0!=products_list_mysql.size() && null!= products_list_mysql){
            System.out.println("进入不为空判断方法体内，准备执行for循环");
            //循环存入redis
            for(int i =0;i<products_list_mysql.size();i++){
                System.out.println("进入for循环内");
                //把mysql查询到的实体类转换成JSON
                String productJSON = JSONUtil.toJsonString(products_list_mysql.get(i));
                String setStatu = redisService.set(REDIS_PRODUCT_KEY + products_list_mysql.get(i).getProductId(), productJSON);
                System.out.println("redis中的key值为："+REDIS_PRODUCT_KEY + products_list_mysql.get(i).getProductId());
                System.out.println("存入redis是否成功"+setStatu);



            }
            return true;
        }



        return false;
    }







    public List<MemberProduct> getProductListByPrimayKeyFromRedis (List<Long> list, RedisService redisService){
        String product_JSON = null;

        //首先判断list的长度，如果不为空则查询redis
        if(0!=list.size() || null!=list){
            //for循环遍历list数组，用所拿到的Long查询redis中的商品信息
            List<MemberProduct> Product_List = new ArrayList<MemberProduct>();
            try {
            for(int i=0;i<list.size();i++){

                    product_JSON = redisService.get(REDIS_PRODUCT_KEY+list.get(i));//PREFIX_PRODUCT为redis中商品数据的KEY值固定前缀，使用静态常量解决硬编码

                //然后把字符串类型的JSON数据转换成Product实体类对象
                //考虑脏读问题，有可能查询的瞬间商家把该商品删除了，此处要做Redis返回数据的非空判断
                if(""!=product_JSON || product_JSON!=null){
                    // 如果数据不为空，则添加进Product_List中，否则不作任何处理，直接进入下一次循环
                    MemberProduct p = JSONUtil.toObject(product_JSON, MemberProduct.class);
                    Product_List.add(p);
                }

            }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            //for循环结束之后，返回Product_List
            return Product_List;

        }
        //如果list长度为空，则返回msg：没有相关商品！
        return null;


    }

    public MemberProduct getProductByPrimayKeyFromRedis (Long productId, RedisService redisService){
        String product_JSON = null;

        //首先判断list的长度，如果不为空则查询redis
        if(0!=productId || null!=productId){
            //for循环遍历list数组，用所拿到的Long查询redis中的商品信息

            try {


                    product_JSON = redisService.get(REDIS_PRODUCT_KEY+productId);//PREFIX_PRODUCT为redis中商品数据的KEY值固定前缀，使用静态常量解决硬编码


                    //然后把字符串类型的JSON数据转换成Product实体类对象
                    //考虑脏读问题，有可能查询的瞬间商家把该商品删除了，此处要做Redis返回数据的非空判断
                    if(""!=product_JSON || product_JSON!=null){
                        // 如果数据不为空，则添加进Product_List中，否则不作任何处理，直接进入下一次循环
                        MemberProduct p = JSONUtil.toObject(product_JSON, MemberProduct.class);
                        return p;
                    }


            }catch (Exception e){
                e.printStackTrace();

            }


        }

        return null;


    }


    public boolean insertZeroCatToRedis(RedisService redisService, MyRedisService myRedisService){
        //查询所有level=0的类目信息
        List<ProductCat> productCats = productCatMapper.getAllLevelIsZero();
        //存入redis
        String status = redisService.set(REDIS_LEVEL0CAT_KEY, JSONUtil.toJsonString(productCats));
        if ("OK".equals(status.toUpperCase())){
            return true;
        }

        return false;
    }




    public boolean insertAllShopInfoToRedis(RedisService redisService) {

        System.out.println("进入存储方法内");
        //1、首先查询全部的mysql商品信息
        List<ShopInfoCutom> shopInfoCutoms = null;
        try {
            shopInfoCutoms = shopInfoMapper.getAllShopInfo();
            System.out.println("查看mysql数据长度"+shopInfoCutoms.size());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        //2、如果查询到的mysql商品不为空，则存入redis
        if (0!=shopInfoCutoms.size() && null!= shopInfoCutoms){
            System.out.println("进入不为空判断方法体内，准备执行for循环");
            //循环存入redis
            for(int i =0;i<shopInfoCutoms.size();i++){
                System.out.println("进入for循环内");
                //把mysql查询到的实体类转换成JSON
                String shopInfoJSON = JSONUtil.toJsonString(shopInfoCutoms.get(i));
                String setStatu = redisService.set(REDIS_SHOPINFO_KEY +shopInfoCutoms.get(i).getId(), shopInfoJSON);
                System.out.println("redis中的key值为："+REDIS_SHOPINFO_KEY + shopInfoCutoms.get(i).getId());
                System.out.println("存入redis是否成功"+setStatu);



            }
            return true;
        }



        return false;
    }







    public ShopInfoCutom getShopInfoCutomByPrimayKeyFromRedis (Long shopId,RedisService redisService){
        try {
            String s = redisService.get(REDIS_SHOPINFO_KEY + shopId);
            ShopInfoCutom shopInfoCutom = JSONUtil.toObject(s, ShopInfoCutom.class);
            if (null!=shopInfoCutom)return shopInfoCutom;
        }catch (Exception e){
            e.printStackTrace();

        }

        return null;
    }


    public boolean insertAllShopMenuAndProductToRedis(RedisService redisService){
        System.out.println("进入存储方法内");
        //1、首先查询全部的商品类目信息
        List<CanTeenDateVo> canTeenDateVos = null;
        int size = 0;
        try {

            canTeenDateVos = productInfoMapper.selectAllShopMenuAndProductToRedis();
            System.out.println("看一下第一条数据的店铺id"+canTeenDateVos.get(0).getShopId());
            size = canTeenDateVos.size();//这步可能出现空指针异常，故trycatch
            System.out.println("查看mysql数据长度"+size);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



        //2、如果查询到的mysql商品不为空，则存入redis
        if (0!=size || null!= canTeenDateVos){
            System.out.println("进入不为空判断方法体内，准备执行for循环");
            ArrayList<CanTeenDateVo> nowCanTeenDateVos = new ArrayList<CanTeenDateVo>();


            //循环存入redis
            Long shopId = 0l;
            String setstatus = "";

            for(int i =0;i<size;i++){
                System.out.println("进入for循环内");
                /* ————————————————————————————————————————新增逻辑————————————————————————————————————-*/
                Long nowShopId = canTeenDateVos.get(i).getShopId();
                if (i==0)shopId=nowShopId;//第一次插入时的判断

                if (nowShopId==shopId){
                    nowCanTeenDateVos.add(canTeenDateVos.get(i));//放入容器中
                    if (i==size-1){//最后一条插入时的判断：
                        //然后存入redis
                        System.out.println("最后一次插入："+nowCanTeenDateVos);
                        String s = JSONUtil.toJsonString(nowCanTeenDateVos);
                        setstatus = redisService.set(REDIS_SHOPMENUANDPRODUCT_KEY + shopId, s);
                        System.out.println("redis中的key值为："+REDIS_SHOPMENUANDPRODUCT_KEY + shopId);
                        System.out.println("存入redis是否成功"+setstatus);
                        //存入完毕
                    }

                }else {//如果不等于上一条数据的shopId，则新创建容器List，！！并且，把上一个List容器的信息存入redis!!

                    //然后存入redis
                    System.out.println("非最后一次的插入"+nowCanTeenDateVos);
                    String s = JSONUtil.toJsonString(nowCanTeenDateVos);
                    setstatus = redisService.set(REDIS_SHOPMENU_KEY + shopId, s);
                    System.out.println("redis中的key值为："+REDIS_SHOPMENU_KEY + shopId);
                    System.out.println("存入redis是否成功"+setstatus);
                    //存入完毕
                    shopId = nowShopId;//覆盖shopId
                    nowCanTeenDateVos = new ArrayList<CanTeenDateVo>();//覆盖新的List容器
                    nowCanTeenDateVos.add(canTeenDateVos.get(i));//放入容器中

                }
                shopId = canTeenDateVos.get(i).getShopId();//获取当前准备插入redis中条数的shopId



                /* ————————————————————————————————————————新增逻辑————————————————————————————————————-*/











            }
            return true;
        }
        return false;
    }




    public List<CanTeenDateVo> getShopMenuAndProductByShopIdToRedis (Long shopId, RedisService redisService){
        String menuAndProduct_JSON = null;

        //首先判断list的长度，如果不为空则查询redis
        if(0!=shopId || null!=shopId){
            try {


                menuAndProduct_JSON = redisService.get(REDIS_SHOPMENUANDPRODUCT_KEY+shopId);//PREFIX_PRODUCT为redis中商品数据的KEY值固定前缀，使用静态常量解决硬编码


                //然后把字符串类型的JSON数据转换成Product实体类对象
                //考虑脏读问题，有可能查询的瞬间商家把该商品删除了，此处要做Redis返回数据的非空判断
                if(""!=menuAndProduct_JSON || menuAndProduct_JSON!=null){
                    // 如果数据不为空，则添加进Product_List中，否则不作任何处理，直接进入下一次循环
                    List<CanTeenDateVo>  p = JSONUtil.toList(menuAndProduct_JSON, CanTeenDateVo.class);
                    return p;
                }


            }catch (Exception e){
                e.printStackTrace();

            }


        }

        return null;


    }


    public boolean coverShopMenuAndProductByShopIdInRedis(Long shopId,RedisService redisService){
        //首先进行单查询，从mysql中查询该店铺的Canteen数据
        List<CanTeenDateVo> canteenDateByShopId = productInfoMapper.getCanteenDateByShopId(shopId);
        String s = JSONUtil.toJsonString(canteenDateByShopId);
        //然后覆盖旧数据
        String setstatus = redisService.set(REDIS_SHOPMENUANDPRODUCT_KEY + shopId, s);
        if ("OK".equals(setstatus.toUpperCase()))return true;


        return false;
    }

    public boolean coverShopInfoByShopIdInRedis(Long shopId,RedisService redisService){
        //首先进行单查询，从mysql中查询该店铺的Canteen数据
        ShopInfoCutom shopAllInfoLAOYANG = shopInfoMapper.getShopAllInfoLAOYANG(shopId);
        String s = JSONUtil.toJsonString(shopAllInfoLAOYANG);
        //然后覆盖旧数据
        String setstatus = redisService.set(REDIS_SHOPINFO_KEY + shopId, s);
        if ("OK".equals(setstatus.toUpperCase()))return true;


        return false;
    }















}
