package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息收集表单配置表
 * @TableName infoform
 */
@TableName(value ="infoform")
@Data
public class Infoform implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer iIFId;

    /**
     * 创建人编号
     */
    private String cIF_cPICode_insert;

    /**
     * 创建表名称
     */
    private String cIFTableName;

    /**
     * 表单发布者（本人、组织等）
     */
    private String cIFPuber;

    /**
     * 表单标题
     */
    private String cIFTitle;

    /**
     * 创建时间
     */
    private Date cIFCreateTime;

    /**
     * 修改人编号
     */
    private String cIF_cPICode_update;

    /**
     * 更新时间
     */
    private Date cIFUpdateTime;

    /**
     * 发布时间
     */
    private Date cIFPubTime ;

    /**
     * 表单状态（草稿、新增、发布）
     */
    private String cIFState;

    /**
     * 数据管理员
     */
    private String cIFManager;

    /**
     * 数据发布人员
     */
    private String cIFPubStaff;

    /**
     * 数据发布组织
     */
    private String cIFPubOrg;

    /**
     * 数据发布对象标签
     */
    private String cIFPubFlag;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}