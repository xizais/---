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
 * @TableName areainfo
 */
@TableName(value ="areainfo")
@Data
public class Areainfo implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer iAIId;

    /**
     * 管理人编号
     */
    private String cAIManagerCode;

    /**
     * 管理人名称
     */
    private String cAIManagerName;

    /**
     * 管理人联系方式
     */
    private String cAIManagerPhone;

    /**
     * 场地名称
     */
    private String cAIName;

    /**
     * 场地地点
     */
    private String cAIContent;

    /**
     * 是否启用场地
     */
    private Boolean bAIIsEnable;

    /**
     * 预约是否需要审批
     */
    private Boolean bAIIsApprove;

    /**
     * 创建时间
     */
    private Date dAICreateTime;

    /**
     * 修改时间
     */
    private Date dAIUpdateTime;

    /**
     * 处理人ID
     */
    private String cAIHandleCode;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}