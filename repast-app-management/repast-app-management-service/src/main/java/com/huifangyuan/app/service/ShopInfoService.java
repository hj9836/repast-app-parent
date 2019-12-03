package com.huifangyuan.app.service;

import com.huifangyuan.app.cutom.ShopInfoCutom;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.utils.JSONUtil;
import com.huifangyuan.app.vo.AdvertiseVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import static com.huifangyuan.app.staticstatus.StaticProperties.*;


@Service
public class ShopInfoService {

    @Autowired
    private ShopInfoMapper shopInfoMapper;





    public ShopInfoVo getShopInfoByPrimaryKey(Long ShopId,RedisService redisService,MyRedisService myRedisService) {
        //首先从redis中获取
        ArrayList<Long> list = new ArrayList<Long>();
        list.add(ShopId);//这里没做方法重载，所以只有一条数据也要添加进list中传入，等待后续优化 //TODO
        List<ShopInfoVo> shopInfoListByPrimayKeyFromRedis = myRedisService.getShopInfoListByPrimayKeyFromRedis(list, redisService);
        System.out.println("瞅一眼从redis里拿到的商家数据"+shopInfoListByPrimayKeyFromRedis);
        System.out.println(null!=shopInfoListByPrimayKeyFromRedis);

        if (null!=shopInfoListByPrimayKeyFromRedis){

            return shopInfoListByPrimayKeyFromRedis.get(0);
        }


        //否则返回原来的方法
        return shopInfoMapper.getShopInfoByPrimaryKey(ShopId);
    }


    /**
     *  根据店铺的主键查询店铺的所有信息
     *
     *
     * @param shopId
     * @return
     */
    public ShopInfoVo getShopAllInfoByShopId(Long shopId){
        ShopInfoVo shopAllInfoByShop = shopInfoMapper.getShopAllInfoByShopId(shopId);
        return shopAllInfoByShop;
    }

    public List<AdvertiseVo> getAdvertise(){

        return shopInfoMapper.getAdvertise();
    }

    public ShopInfoCutom getShopAllInfoLAOYANG(Long shopId,RedisService redisService){
        //先从redis中读取：
        try {
            String s = redisService.get(REDIS_SHOP_KEY + shopId);
            ShopInfoCutom shopInfoCutom = JSONUtil.toObject(s, ShopInfoCutom.class);
            if (null!=shopInfoCutom)return shopInfoCutom;
        }catch (Exception e){
            e.printStackTrace();

        }


        return shopInfoMapper.getShopAllInfoLAOYANG(shopId);




    }



}
