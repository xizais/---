package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 人员信息表
 * @TableName personinfo
 */
@TableName(value ="personinfo")
@Data
public class Personinfo implements Serializable {
    /**
     * 人员编码
     */
    @TableId
    private String cPICode;

    /**
     * 人员名称
     */
    private String cPIName;

    /**
     * 性别
     */
    private Object cPIGender;

    /**
     * 出生年月
     */
    private Date dPIBirthDate;

    /**
     * 户籍地
     */
    private String cPIHometown;

    /**
     * 联系电话
     */
    private String cPIContactNumber;

    /**
     * 邮箱
     */
    private String cPIEmail;

    /**
     * 身份证类型
     */
    private String cPIIDType;

    /**
     * 身份证号码
     */
    private String cPIIDNumber;

    /**
     * 身份
     */
    private String cPIIdentity;

    /**
     * 所属组织代号
     */
    private String cPI_cOICode;

    /**
     * 创建时间
     */
    private Date dPICreateTime;

    /**
     * 密码
     */
    private String cPIPassword;

    /**
     * 是否启用
     */
    private Boolean bPIIsEnabled;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}