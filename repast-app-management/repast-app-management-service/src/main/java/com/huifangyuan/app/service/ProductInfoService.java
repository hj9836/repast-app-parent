package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.CanTeenDateVo;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.huifangyuan.app.staticstatus.StaticProperties.REDIS_LEVEL0CAT_KEY;

@Service
public class ProductInfoService extends BaseService<Product> {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductCatMapper productCatMapper;

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

    /**@ClassName ProductService
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


    public List<MemberProduct>  test(Long ShopId,RedisService redisService,MyRedisService myRedisService){
        //原来的方法返回的是List<MemberProduct>,现在改为List<Long>
        List<Long> productId_list = productInfoMapper.selectProductIdByShopId(ShopId);
        //然后调用我这个方法——>getProductListByPrimayKeyFromRedis 把查询到的List<Long>传入即可
        List<MemberProduct> productListByPrimayKeyFromRedis = myRedisService.getProductListByPrimayKeyFromRedis(productId_list, redisService);
        if (null==productListByPrimayKeyFromRedis || 0==productListByPrimayKeyFromRedis.size()){
            //如果redis挂了，或者查询到的值为空，则执行原来所写的代码即可
            List<MemberProduct> productListByPrimayKeyFromMysql = productInfoMapper.selectProductByShopId(ShopId);
            return productListByPrimayKeyFromMysql;
        }

        return productListByPrimayKeyFromRedis;
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


    /**
     * 通过店铺id查询当前店铺的所有上线状态商品
     * @param shopId
     * @return
     */
    public List<MemberProduct> getAllProductByShopId(Long shopId,RedisService redisService,MyRedisService myRedisService){
        List<Long> ListId = productInfoMapper.getAllProductIdByShopId(shopId);
        List<MemberProduct> productListByPrimayKeyFromRedis = myRedisService.getProductListByPrimayKeyFromRedis(ListId, redisService);
        if (null == productListByPrimayKeyFromRedis || 0 == productListByPrimayKeyFromRedis.size()){
            List<MemberProduct> allProductByShopId = productInfoMapper.getAllProductByShopId(shopId);
            return allProductByShopId;
        }
        return productListByPrimayKeyFromRedis;
    }

    /**
     * 根据商铺的ID查询当前店铺所推荐的商品信息
     * @param shopId
     * @return
     */
    public List<MemberProduct> getAllShopRecommendProductByShopId(Long shopId,RedisService redisService,MyRedisService myRedisService){
        List<Long> listId = productInfoMapper.getAllShopRecommendProductIdByShopId(shopId);
        List<MemberProduct> productListByPrimayKeyFromRedis = myRedisService.getProductListByPrimayKeyFromRedis(listId, redisService);
        if (null == productListByPrimayKeyFromRedis || 0 == productListByPrimayKeyFromRedis.size()){
            List<MemberProduct> allShopRecommendProductByShopId = productInfoMapper.getAllShopRecommendProductByShopId(shopId);
            System.out.println(allShopRecommendProductByShopId);
            return allShopRecommendProductByShopId;
        }
        return productListByPrimayKeyFromRedis;
    }


    public List<MemberProduct> getRecommandProduct(Integer pageNum,Integer pageSize,RedisService redisService,MyRedisService myRedisService){
        System.out.println(pageNum);
        System.out.println(pageSize);
        //首先计算起始页startPage
        if (pageSize<1){
            pageSize=4;
        }
        Integer startPage=0;
        startPage=(pageNum-1)*pageSize;
        if (startPage<0){
            startPage=0;
        }
        System.out.println(startPage);
        //然后去redis中拿取数据
        HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
        pageMap.put("startPage",startPage);
        pageMap.put("pageSize",pageSize);

        List<Long> list = productInfoMapper.getRecommandProductId(pageMap);
        System.out.println(list);
        List<MemberProduct> productListByPrimayKeyFromRedis = myRedisService.getProductListByPrimayKeyFromRedis(list, redisService);
        if (null!=productListByPrimayKeyFromRedis){
            System.out.println("进入redis中查询到的商品不为空中");
            return productListByPrimayKeyFromRedis;
        }

        System.out.println(getProductListByPrimaryKeyList(list).get(0).getMemberPrice());
        return getProductListByPrimaryKeyList(list);
    }



    public MemberProduct getProductInfoByPrimaryKey(Long productId,RedisService redisService,MyRedisService myRedisService){
        MemberProduct productByPrimayKeyFromRedis = myRedisService.getProductByPrimayKeyFromRedis(productId, redisService);
        if (null!=productByPrimayKeyFromRedis) {
            return productByPrimayKeyFromRedis;
        }
        return productInfoMapper.getProductInfoByPrimaryKey(productId);

    }

    public List<CanTeenDateVo> getCanteenDateByShopId(Long shopId,RedisService redisService,MyRedisService myRedisService){

        //首先进入redis中查询
        List<CanTeenDateVo>  shopMenuAndProductByShopIdToRedis = myRedisService.getShopMenuAndProductByShopIdToRedis(shopId, redisService);
        if (null!=shopMenuAndProductByShopIdToRedis){
            return shopMenuAndProductByShopIdToRedis;
        }


        return productInfoMapper.getCanteenDateByShopId(shopId);
    }























    /*--------------------------------根据List<Long>返回List<MemberProduct>的工具类------------------------------------*/
    //此方法相当于工具类，但一定不能用static修饰，否则会线程串线！！！
    public List<MemberProduct> getProductListByPrimaryKeyList(List<Long> productList){
        System.out.println("进入半工具类方法中");
        /*if (productList.size()==0){
            //一定要做这步判断，不然当list中没有参数时，动态sql查询出来的是全部商品！
            return null;
        }*/
        Map<String, List<Long>> list = new HashMap<String, List<Long>>();
        list.put("list",productList);
        System.out.println(productInfoMapper.getProductListByPrimaryKeyList(list).get(0).getName());
        return productInfoMapper.getProductListByPrimaryKeyList(list);

    }
    /*--------------------------------根据List<Long>返回List<MemberProduct>的工具类------------------------------------*/





}
