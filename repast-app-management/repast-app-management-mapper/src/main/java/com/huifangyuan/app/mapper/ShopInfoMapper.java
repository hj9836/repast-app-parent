package com.huifangyuan.app.mapper;

import com.huifangyuan.app.cutom.ShopInfoCutom;
import com.huifangyuan.app.vo.AdvertiseVo;
import com.huifangyuan.app.vo.ShopInfoVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


//店铺信息目前没有单表查询，所以暂时不继承通用mapper
public interface ShopInfoMapper {


    ShopInfoVo getShopInfoByPrimaryKey (Long ShopId);

    List<ShopInfoCutom> getAllShopInfo();

    ShopInfoVo getShopAllInfoByShopId(Long shopId);

    List<AdvertiseVo> getAdvertise();

    ShopInfoCutom getShopAllInfoLAOYANG(Long shopId);
}
