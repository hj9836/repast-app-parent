package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MemberProduct implements Serializable {
   private long Id;
   private String name;
   private double price;
   private double promotionPrice;
   private int promotionType;
   private double memberPrice;

}
