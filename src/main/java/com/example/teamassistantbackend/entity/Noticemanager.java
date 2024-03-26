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
 * @TableName noticemanager
 */
@TableName(value ="noticemanager")
@Data
public class Noticemanager implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer iNMId;

    /**
     * 通知管理人员code
     */
    private String cNMManagerCodes;

    /**
     * 通知发布者名称
     */
    private String cNMPubName;

    /**
     * 通知标题
     */
    private String cNMTitle;

    /**
     * 通知内容
     */
    private String cNMContent;

    /**
     * 通知状态
     */
    private String cNMState;

    /**
     * 通知创建时间
     */
    private Date dNMCreateTime;

    /**
     * 通知修改时间
     */
    private Date dNMUpdateTime;

    /**
     * 通知创建人员
     */
    private String cNMCreaterCode;

    /**
     * 通知修改人员
     */
    private String cNMUpdaterCode;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}