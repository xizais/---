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
 * @TableName areabook
 */
@TableName(value ="areabook")
@Data
public class Areabook implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer iABId;

    /**
     * 场地表ID
     */
    private Integer iAB_iAIId;

    /**
     * 预约人员
     */
    private String cABBookerCode;

    /**
     * 预约开始时间
     */
    private Date dABBookStartTime;

    /**
     * 预约结束时间
     */
    private Date dABBookEndTime;

    /**
     * 申请内容（255字以内）
     */
    private String cABContent;

    /**
     * 申请状态（草稿、待审批、未通过、已通过）
     */
    private String cABState;

    /**
     * 创建时间
     */
    private Date dABCreateTime;

    /**
     * 修改时间
     */
    private Date dABUpdateTime;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}