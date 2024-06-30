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
 * @TableName chatinfo
 */
@TableName(value ="chatinfo")
@Data
public class Chatinfo implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer cCIId;

    /**
     * 发送方编号
     */
    private String cCISelfCode;

    /**
     * 接收方编号
     */
    private String cCIOtherCode;

    /**
     * 发送内容
     */
    private String cCIContent;

    /**
     * 发送时间
     */
    private Date dCISendTime;

    /**
     * 信息状态（未读、已读）
     */
    private String cCIState;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}