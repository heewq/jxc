package com.atguigu.jxc.entity;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
/**
 * 报损
 */
@Data
public class DamageList {

  private Integer damageListId;
  private String damageNumber;
  private String damageDate;
  private String remarks;
  private Integer userId;

  private String trueName;

}
