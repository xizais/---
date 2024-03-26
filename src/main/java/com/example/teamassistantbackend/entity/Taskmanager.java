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
 * @TableName taskmanager
 */
@TableName(value ="taskmanager")
@Data
public class Taskmanager implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer iTMId;

    /**
     * 任务管理人员code
     */
    private String cTMManagerCodes;

    /**
     * 任务发布者名称
     */
    private String cTMPubName;

    /**
     * 任务标题
     */
    private String cTMTitle;

    /**
     * 任务要求
     */
    private String cTMRequest;

    /**
     * 任务内容
     */
    private String cTMContent;

    /**
     * 任务状态
     */
    private String cTMState;

    /**
     * 任务创建时间
     */
    private Date dTMCreateTime;

    /**
     * 任务修改时间
     */
    private Date dTMUpdateTime;

    /**
     * 任务创建人员
     */
    private String cTMCreaterCode;

    /**
     * 任务修改人员
     */
    private String cTMUpdaterCode;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}