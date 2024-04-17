package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件管理表，用于存储文件相关信息
 * @TableName filemanager
 */
@TableName(value ="filemanager")
@Data
public class Filemanager implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer iFMId;

    /**
     * 文件信息表ID
     */
    private Integer iFM_iFICode;

    /**
     * 文件所属类型，例如：任务、通知、组织文件
     */
    private String cFMType;

    /**
     * 文件所属类型表ID
     */
    private String cFMTypeId;

    /**
     * 文件所属人员code
     */
    private String cFMManagerCode;

    /**
     * 处理时间，默认为当前修改时间
     */
    private Date dFMHandleTime;

    /**
     * 处理人员code
     */
    private String cFMHandleCode;

    /**
     * 数据状态（0：活动；1：删除），默认为0
     */
    private String dataState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}