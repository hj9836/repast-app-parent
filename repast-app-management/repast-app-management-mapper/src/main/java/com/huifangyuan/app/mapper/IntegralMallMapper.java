package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.IntegralMall;

import java.util.List;

/**
 * @author Regina
 * @data 2019/11/28 9:13
 * @project repast-app-parent
 * @declaration:
 */
public interface IntegralMallMapper {
    /**
     * 查询支持用积分兑换的商品信息
     * @return
     */
    List<IntegralMall> getProductInfoByIntegral();
}
