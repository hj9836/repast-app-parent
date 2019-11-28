package com.huifangyuan.app.service;

import com.huifangyuan.app.domain.IntegralMall;
import com.huifangyuan.app.mapper.IntegralMallMapper;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Regina
 * @data 2019/11/28 9:15
 * @project repast-app-parent
 * @declaration:
 */
@Service
public class IntegralMallService {
    @Autowired
    private IntegralMallMapper integralMallMapper;

    /**
     * 查询支持用积分兑换的商品信息
     * @return
     */
    public List<MemberProduct> getProductInfoByIntegral(Integer pageNum,Integer pageSize,RedisService redisService,MyRedisService myRedisService){
        //首先进入redis查询
        Map<String, Integer> pagemap = new HashMap<String, Integer>();
        Integer startNum = 0;
        if (pageSize<1){
            pageSize=1;
        }
        startNum=(pageNum-1)*pageSize;
        if (startNum<0){
            startNum=0;
        }
        pagemap.put("startNum",startNum);
        pagemap.put("pageSize",pageSize);

        System.out.println(startNum);
        System.out.println(pageSize);
        List<Long> list = integralMallMapper.getProductIdByIntegral(pagemap);
        List<MemberProduct> productListByPrimayKeyFromRedis = myRedisService.getProductListByPrimayKeyFromRedis(list, redisService);
        if (null!=productListByPrimayKeyFromRedis){
            System.out.println("进入redis不为空方法中");
            return productListByPrimayKeyFromRedis;
        }
        System.out.println(integralMallMapper.getProductInfoByIntegral(pagemap).get(0).getName());
        return integralMallMapper.getProductInfoByIntegral(pagemap);

    }
}
