package com.huifangyuan.app.cutom;

import com.huifangyuan.app.domain.ShopInfo;
import com.huifangyuan.app.domain.ShopInfoFacility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShopInfoCutom implements Serializable {
    private Long id;

    private String name;

    private String province;

    private String city;

    private String borough;

    private String address;

    private String lng;

    private String lat;

    private Byte closed;

    @Column(name = "open_time")
    private String openTime;

    private String phone;

    private String images;

    /**
     * 0：冻结状态，1：正常，2：违规关闭，3：店铺到期关闭
     */
    private Byte status;

    @Column(name = "auth_start_time")
    private Date authStartTime;

    @Column(name = "auth_long")
    private int authLong;






    private List<ShopInfoFacility> shopInfoFacilities;
}
