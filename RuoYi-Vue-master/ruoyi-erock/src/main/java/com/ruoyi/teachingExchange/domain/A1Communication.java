package com.ruoyi.teachingExchange.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1交流对象 a1_communication
 *
 * @author heye
 * @date 2024-03-20
 */
public class A1Communication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子ID */
    private Long comId;

    /** 教学资源ID */
    @Excel(name = "教学资源ID")
    private Long teachingId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String comContent;

    /** 评论者 */
    @Excel(name = "评论者")
    private Long comBy;

    /** 评论时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评论时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date comTime;

    /** 主贴回复ID */
    @Excel(name = "主贴回复ID")
    private Long comPid;

    /** 评论者 */
    @Excel(name = "评论者")
    private RemarkPerson remarkSelf;

    /** 被评论者 */
    @Excel(name = "被评论者")
    private RemarkPerson remarkTo;

    /** 回复列表 */
    @Excel(name = "回复列表")
    private List<A1Communication> replys;

    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setTeachingId(Long teachingId)
    {
        this.teachingId = teachingId;
    }

    public Long getTeachingId()
    {
        return teachingId;
    }
    public void setComContent(String comContent)
    {
        this.comContent = comContent;
    }

    public String getComContent()
    {
        return comContent;
    }
    public void setComBy(Long comBy)
    {
        this.comBy = comBy;
    }

    public Long getComBy()
    {
        return comBy;
    }
    public void setComTime(Date comTime)
    {
        this.comTime = comTime;
    }

    public Date getComTime()
    {
        return comTime;
    }
    public void setComPid(Long comPid)
    {
        this.comPid = comPid;
    }

    public Long getComPid()
    {
        return comPid;
    }

    public RemarkPerson getRemarkSelf() {
        return remarkSelf;
    }

    public void setRemarkSelf(RemarkPerson remarkSelf) {
        this.remarkSelf = remarkSelf;
    }

    public RemarkPerson getRemarkTo() {
        return remarkTo;
    }

    public void setRemarkTo(RemarkPerson remarkTo) {
        this.remarkTo = remarkTo;
    }

    public List<A1Communication> getReplys() {
        return replys;
    }

    public void setReplys(List<A1Communication> replys) {
        this.replys = replys;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("comId", getComId())
                .append("teachingId", getTeachingId())
                .append("comContent", getComContent())
                .append("comBy", getComBy())
                .append("comTime", getComTime())
                .append("comPid", getComPid())
                .append("RemarkTo", getRemarkTo())
                .append("RemarkSelf", getRemarkSelf())
                .append("replys", getReplys())
                .toString();
    }
}
