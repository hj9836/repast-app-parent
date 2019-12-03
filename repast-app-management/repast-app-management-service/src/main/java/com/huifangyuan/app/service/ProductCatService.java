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
        //先走redis查询：

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
    public List<ProductCat> selectShopMenuByShopIdToRedis(Long shopId, RedisService redisService, ShopmenuRedisService shopmenuRedisService){

        List<ProductCat> productCatListByPrimayKeyFromRedis = shopmenuRedisService.getShopMenuByShopIdFromRedis(shopId, redisService);
        try {//预防空指针异常
            if (null!=productCatListByPrimayKeyFromRedis || 0!=productCatListByPrimayKeyFromRedis.size()){
                return productCatListByPrimayKeyFromRedis;

            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //如果redis挂了，或者查询到的值为空，则执行原来所写的代码即可
        List<ProductCat> productCatList = productCatMapper.selectProductCatByshopId(shopId);
        return productCatList;

    }





    public List<ProductCat> getZeroCat(RedisService redisService){
        //首先从redis中获取
        String s = null;
        try {
            s = redisService.get(REDIS_LEVEL0CAT_KEY);
            List<ProductCat> productCats = JSONUtil.toList(s, ProductCat.class);
            if (null!=productCats){
                return productCats;

            }
        }catch (Exception e){
            e.printStackTrace();
        }





        return productCatMapper.getAllLevelIsZero();
    }

}
