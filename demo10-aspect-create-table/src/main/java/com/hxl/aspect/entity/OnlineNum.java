package com.hxl.aspect.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 在线人数实体类
 *
 * @author hengxiaoliang
 */

// TODO: true => 在equals方法比较时，会考虑继承的父类的属性
@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineNum extends BaseEntity {

    /**
     * 主键
     */
    private Integer id;

     /**
      * 直播间 id
      */
    private Integer roomId;

     /**
      * 总在线人数
      */
    private Integer totalOnlineNum;

     /**
      * 开始时间
      */
    private String startTime;

     /**
      * 结束时间
      */
     private String endTime;
}
