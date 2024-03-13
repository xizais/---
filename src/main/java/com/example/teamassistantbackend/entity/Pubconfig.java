package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发布配置表
 * @TableName pubconfig
 */
@TableName(value ="pubconfig")
@Data
public class Pubconfig implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer iPCId;

    /**
     * 关联表ID
     */
    private Integer iTypeId;

    /**
     * 关联表类型
     */
    private String cType;

    /**
     * 发布开始时间
     */
    private Date dPubStartTime;

    /**
     * 发布停止时间
     */
    private Date dPubEndTime;

    /**
     * 是否进行审核
     */
    private String cIsApproval;

    /**
     * 用户最大填写记录数量
     */
    private Integer iMaxRecordCount;

    /**
     * 是否启用组织管理
     */
    private String cIsOrgManger;

    /**
     * 发布者
     */
    private String cPuber;

    /**
     * 发布人员
     */
    private String cPubToPerson;

    /**
     * 发布组织
     */
    private String cPubToOrg;

    /**
     * 发布标识（标签ID）
     */
    private String cPubToFlag;

    /**
     * 创建时间
     */
    private Date dCreateTime;

    /**
     * 修改时间
     */
    private Date dUpdateTime;

    /**
     * 创建者
     */
    private String cCreator;

    /**
     * 修改者
     */
    private String cUpdater;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}