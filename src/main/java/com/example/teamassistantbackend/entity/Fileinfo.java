package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件信息表
 * @TableName fileinfo
 */
@TableName(value ="fileinfo")
@Data
public class Fileinfo implements Serializable {
    /**
     * 文件编码
     */
    @TableId(type = IdType.AUTO)
    private int iFICode;

    /**
     * 文件名
     */
    private String cFIFileName;

    /**
     * MD5编码
     */
    private String cFIMD5Code;

    /**
     * 文件路径
     */
    private String cFIFilePath;

    /**
     * 创建时间
     */
    private Date dFICreateTime;

    /**
     * 创建人
     */
    private String cFI_cPICode;

    /**
     * 文件描述
     */
    private String cFIDescription;

    /**
     * 文件大小（字节）
     */
    private Long iFIFileSize;

    /**
     * 文件类型
     */
    private String cFIMimeType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}