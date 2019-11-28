package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.IntegralMall;
import com.huifangyuan.app.vo.MemberProduct;

import java.util.List;
import java.util.Map;

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
    List<MemberProduct> getProductInfoByIntegral(Map<String,Integer> map);

    List<Long> getProductIdByIntegral(Map<String,Integer> map);

}
