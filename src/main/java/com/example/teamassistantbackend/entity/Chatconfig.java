package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName chatconfig
 */
@TableName(value ="chatconfig")
@Data
public class Chatconfig implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer iCCId;

    /**
     * 聊天名称
     */
    private String cCCName;

    /**
     * 己方编号
     */
    private String cCCSelfCode;

    /**
     * 对方编号
     */
    private String cCCOtherCode;

    /**
     * 创建时间
     */
    private Date dCreateTime;

    /**
     * 修改时间
     */
    private Date dUpdateTime;

    /**
     * 是否显示在聊天记录
     */
    private Boolean bIsShow;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}