package com.huifangyuan.app.service;

import com.huifangyuan.app.mapper.ShopInfoMapper;
import com.huifangyuan.app.vo.ShopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShopInfoService {

    @Autowired
    private ShopInfoMapper shopInfoMapper;

    public ShopInfoVo getShopInfoByPrimaryKey(Long ShopId) {
        return shopInfoMapper.getShopInfoByPrimaryKey(ShopId);
    }

}
