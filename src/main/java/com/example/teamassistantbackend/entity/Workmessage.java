package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工作信息表
 * @TableName workmessage
 */
@TableName(value ="workmessage")
@Data
public class Workmessage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer iWMId;

    /**
     * 工作类型
     */
    private String type;

    /**
     * 工作类型对应表ID
     */
    private Integer typeId;

    /**
     * 人员编码
     */
    private String personCode;

    /**
     * 人员名称
     */
    private String personName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}