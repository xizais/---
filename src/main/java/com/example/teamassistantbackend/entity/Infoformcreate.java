package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息收集表单创建信息表
 * @TableName infoformcreate
 */
@TableName(value ="infoformcreate")
@Data
public class Infoformcreate implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer iIFCId;

    /**
     * 表单配置表主键
     */
    private Integer iIFC_iIFId;

    /**
     * 页面容器|元素主键
     */
    private Integer id;

    /**
     * 父容器ID
     */
    private Integer containerId;

    /**
     * 宽：百分比显示
     */
    private String width;

    /**
     * 高：px显示
     */
    private String height;

    /**
     * 显示边框
     */
    private String showBorder;

    /**
     * 边框粗度
     */
    private String borderWidth;

    /**
     * 显示圆角
     */
    private String showRadius;

    /**
     * 圆角度数
     */
    private String borderRadius;

    /**
     * 盒子顶部
     */
    private String marginTop;

    /**
     * 盒子底部
     */
    private String marginBottom;

    /**
     * 盒子左边
     */
    private String marginLeft;

    /**
     * 盒子右边
     */
    private String marginRight;

    /**
     * 文本类型
     */
    private String textType;

    /**
     * 字体大小
     */
    private String fontSize;

    /**
     * 字体粗细
     */
    private String fontWeight;

    /**
     * 字体类型
     */
    private String fontFamily;

    /**
     * 默认文本
     */
    private String defaultText;

    /**
     * 文本位置
     */
    private String textAlign;

    /**
     * 文字颜色
     */
    private String textColor;

    /**
     * 文本提示
     */
    private String placeholder;

    /**
     * 最大输入字数个数
     */
    private String maxLength;

    /**
     * 选项值（建议直接存json数组）
     */
    private String options;

    /**
     * 默认时间
     */
    private Date defaultTime;

    /**
     * 是否必填
     */
    private String isNeed;

    /**
     * 类型（text、input、select、textarea、radio、checkbox、time）
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}