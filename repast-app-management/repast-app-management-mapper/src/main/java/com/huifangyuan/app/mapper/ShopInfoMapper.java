package com.huifangyuan.app.mapper;

import com.huifangyuan.app.vo.ShopInfoVo;
import tk.mybatis.mapper.common.Mapper;


//店铺信息目前没有单表查询，所以暂时不继承通用mapper
public interface ShopInfoMapper {


    ShopInfoVo getShopInfoByPrimaryKey (Long ShopId);
}
