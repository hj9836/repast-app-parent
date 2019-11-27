package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import static com.huifangyuan.app.staticstatus.StaticProperties.REDIS_LEVEL0CAT_KEY;

@Service
public class ProductCatService extends BaseService<ProductCat> {

    @Autowired
    private ProductCatMapper productCatMapper;

    /**
        通用mapper方法
     **/
    public Mapper<ProductCat> getMapper() {
        return productCatMapper;
    }

    public List<ProductCat> getCateByLevel(){
        return productCatMapper.getCateByLevel();
    };

    public List<ProductCat> selectProductByshopid(Long shopId){
        List<ProductCat> productCats = productCatMapper.selectProductCatByshopId(shopId);
        if ((productCats.size()>0)){
            return productCats;
        }
        return null;
    }
    /**
     *@ClassName RedisController
     *@Description
     *@Date 21:03 2019/11/26
     *@author eric
     * 根据店铺id查询商品类目信息从redis中
     *@Param
     *@Return
     **/
    public List<ProductCat>  selectProductCatByshopIdToRedis(Long shopId, RedisService redisService, ShopmenuRedisService shopmenuRedisService){
        //原来的方法返回的是List<ProductCat>,现在改为List<Long>
        List<Long> productCat_list = productCatMapper.selectProductCatByShopId(shopId);
        //然后调用我这个方法——>getProductListByPrimayKeyFromRedis 把查询到的List<Long>传入即可
        List<ProductCat> productCatListByPrimayKeyFromRedis = shopmenuRedisService.getProductCatListByPrimayKeyFromRedis(productCat_list, redisService);
        if (null!=productCatListByPrimayKeyFromRedis || 0==productCatListByPrimayKeyFromRedis.size()){
            //如果redis挂了，或者查询到的值为空，则执行原来所写的代码即可
            List<ProductCat> productCatList = productCatMapper.selectProductCatByshopId(shopId);
            return productCatList;
        }

        return productCatListByPrimayKeyFromRedis;
    }





    public List<ProductCat> getZeroCat(RedisService redisService){
        //首先从redis中获取
        String s = null;
        try {
            s = redisService.get(REDIS_LEVEL0CAT_KEY);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<ProductCat> productCats = JSONUtil.toList(s, ProductCat.class);
        if (null!=productCats){
            return productCats;

        }



        return productCatMapper.getAllLevelIsZero();
    }

}
