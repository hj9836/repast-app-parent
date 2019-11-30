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
    @Column(name = "gift_point")
    private Long giftPoint;
    private Integer pageNuml;
    private Integer pageSize;



    /*！！！！！以下三个价格变量不允许进行任何修改，如果数据格式不兼容联系老杨！！！！*/
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "promotion_price")
    private BigDecimal promotionPrice;
    @Column(name = "member_price")
    private BigDecimal memberPrice;
    /*特别不准使用String类型，因为要和订单组做对接，价格数据为String不符合逻辑*/

}
