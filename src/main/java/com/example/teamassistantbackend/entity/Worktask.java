package com.example.teamassistantbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作待办表
 * @TableName worktask
 */
@TableName(value ="worktask")
public class Worktask implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工作类型
     */
    private String type;

    /**
     * 工作ID
     */
    private Integer typeid;

    /**
     * 人员编码
     */
    private String code;

    /**
     * 数据更新时间
     */
    private Date updatetime;

    /**
     * 状态
     */
    private String state;

    /**
     * 内容描述
     */
    private String content;

    /**
     * 数据状态（0：活动；1：删除）
     */
    private String datastate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 工作类型
     */
    public String getType() {
        return type;
    }

    /**
     * 工作类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 工作ID
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 工作ID
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 人员编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 人员编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 数据更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 数据更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 内容描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 数据状态（0：活动；1：删除）
     */
    public String getDatastate() {
        return datastate;
    }

    /**
     * 数据状态（0：活动；1：删除）
     */
    public void setDatastate(String datastate) {
        this.datastate = datastate;
    }
}