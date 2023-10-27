package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 组织信息表
 * @TableName organizationinfo
 */
@TableName(value ="organizationinfo")
@Data
public class Organizationinfo implements Serializable {
    /**
     * 组织ID（显示层级关系）
     */
    @TableId
    private String cOICode;

    /**
     * 组织编码（用户定义）
     */
    private String cOIOrgCode;

    /**
     * 组织名称
     */
    private String cOIOrgName;

    /**
     * 组织描述
     */
    private String cOIOrgDescription;

    /**
     * 组织性质
     */
    private String cOIOrgType;

    /**
     * 是否公示（是否只有本组织的人才可见）
     */
    private Boolean bOIIsPublicVisible;

    /**
     * 组织子级序号（每增加一个子级+1）
     */
    private Integer iOISubLevelSequence;

    /**
     * 创建时间
     */
    private Date dOICreationTime;

    /**
     * 创建人编码
     */
    private String cOI_cPICode;

    /**
     * 负责人编码（负责人有全权限，最多5个）
     */
    private String cOIManagerCode;

    /**
     * 用户是否经审核加入
     */
    private Boolean bOIIsUserVerified;

    /**
     * 管理组织权限
     */
    private String cOIOrgManagementPerm;

    /**
     * 添加(审核)用户权限
     */
    private String cOIAddUserPerm;

    /**
     * 删除用户权限
     */
    private String cOIDeleteUserPerm;

    /**
     * 分配自己权限的权限
     */
    private String cOIAssignPermPerm;

    /**
     * 管理文件权限
     */
    private String cOIManageFilePerm;

    /**
     * 是否公开组织文件
     */
    private Boolean bOIIsPublicOrgFile;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}