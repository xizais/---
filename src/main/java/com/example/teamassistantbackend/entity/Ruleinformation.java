package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 规则信息表
 * @TableName ruleinformation
 */
@TableName(value ="ruleinformation")
@Data
public class Ruleinformation implements Serializable {
    /**
     * 规则ID
     */
    @TableId
    private String cRIRuleId;

    /**
     * 规则
     */
    private String cRIRule;

    /**
     * 规则类别
     */
    private String cRIRuleCategory;

    /**
     * 当前序号
     */
    private Integer iRICurrentSequence;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}