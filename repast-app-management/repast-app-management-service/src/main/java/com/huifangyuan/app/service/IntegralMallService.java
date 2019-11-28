package com.huifangyuan.app.service;

import com.huifangyuan.app.domain.IntegralMall;
import com.huifangyuan.app.mapper.IntegralMallMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Regina
 * @data 2019/11/28 9:15
 * @project repast-app-parent
 * @declaration:
 */
public class IntegralMallService {
    @Autowired
    private IntegralMallMapper integralMallMapper;

    /**
     * 查询支持用积分兑换的商品信息
     * @return
     */
    public List<IntegralMall> getProductInfoByIntegral(){
        return integralMallMapper.getProductInfoByIntegral();
    }
}
