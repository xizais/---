package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 组织-人员关联表
 * @TableName orgpersonlink
 */
@TableName(value ="orgpersonlink")
@Data
public class Orgpersonlink implements Serializable {
    /**
     * 组织ID
     */
    @TableId(value = "cOPL_cOICode", type = IdType.INPUT)
    private String cOPL_cOICode;

//    @TableId(value = "cOPL_cPICode", type = IdType.INPUT)
//    因为这个表的主键是由组织ID和人员编码组成的，但是mybatis-plus不支持组合的主键，所以这里进行注释；
//    但是还有一种方案是将这两个主键提取出来作为一个单独的实体类，然后这个实体类作为主键，如下：
    /**
     * public class OrgpersonlinkKey implements Serializable {
     *     private String cOPL_cOICode;
     *     private String cOPL_cPICode;
     *
     *     // Getter 和 Setter 方法...
     * }
     *
     * public class Orgpersonlink {
     *     @TableId(type = IdType.INPUT)
     *     private OrgpersonlinkKey orgpersonlinkKey;
     *
     *     // 其他字段...
     *
     *     // Getter 和 Setter 方法...
     * }
     */
    /**
     * 人员编码
     */
    private String cOPL_cPICode;

    /**
     * 创建时间
     */
    private Date dOPLCreationTime;

    /**
     * 创建人
     */
    private String cOPLCreatedBy;

    /**
     * 权限集合
     */
    private String cOPLPermissionSet;

    /**
     * 人员状态
     */
    private Integer iOPLPersonStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}