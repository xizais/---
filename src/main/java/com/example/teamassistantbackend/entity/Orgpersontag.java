package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 组织-人员标签表
 * @TableName orgpersontag
 */
@TableName(value ="orgpersontag")
@Data
public class Orgpersontag implements Serializable {
    /**
     * 标签ID
     */
    @TableId
    private String cOPTTagId;

    /**
     * 标签名称
     */
    private String cOPTTagName;

    /**
     * 创建人编码
     */
    private String cOPT_cPICode;

    /**
     * 创建时间
     */
    private Date dOPTCreationTime;

    /**
     * 包含的组织ID
     */
    private String cOPTIncludedOrgId;

    /**
     * 包含的人员编号
     */
    private String cOPTIncludedPersonCode;

    /**
     * 是否启用
     */
    private Boolean bOPTIsEnabled;

    /**
     * 是否系统公用
     */
    private Boolean bOPTIsSystemShared;

    /**
     * 是否组织公用
     */
    private Boolean bOPTIsOrgShared;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}