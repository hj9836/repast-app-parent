package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:05
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShopInfoVo implements Serializable {

    private Long shopId;
    private String name;
    private String province;
    private String city;
    private String borough;
    private String lng;
    private String lat;
    private String phone;
    private Long facilityId;
    private String title;
    private String description;
    private String address;
    private Long closed;
    @Column(name = "open_time")
    private String openTime;
    private String images;
    private Long status;
    @Column(name = "auth_start_time")
    private Date authStartTime;
    @Column(name = "auth_long")
    private Long authLong;
    @Column(name = "business_license")
    private String businessLicense;
    @Column(name = "food_license")
    private String foodLicense;
    @Column(name = "sanitation_license")
    private String sanitationLicense;
    private String assess;
    private String icon;

}
