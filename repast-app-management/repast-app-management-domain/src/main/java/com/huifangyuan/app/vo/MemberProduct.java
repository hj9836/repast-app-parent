package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MemberProduct implements Serializable {
    private Long shopId;
    private Long productId;
    private Long memberLevelId;

    private String memberLevelName;
    private Long productCategoryId;
    private Integer deleteStatus;
    private Integer publishStatus;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer sale;
    private Integer stock;
    private Integer promotionType;
    private Byte currency;
    private String description;


    private String pic;
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "promotion_price")
    private String promotionPrice;
    @Column(name = "member_price")
    private String  memberPrice;
    @Column(name = "gift_point")
    private Long giftPoint;
    private Integer pageNuml;
    private Integer pageSize;

}
