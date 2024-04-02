package com.ruoyi.teachingExchange.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 线上学习 观看记录对象 a1_viewed
 *
 * @author heye
 * @date 2024-03-27
 */
public class A1Viewed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long viewId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 教学资源ID */
    @Excel(name = "教学资源ID")
    private Long teachingId;

    /** 学生名字 */
    @Excel(name = "学生名字")
    private String stuName;

    /** 学生头像 */
    @Excel(name = "学生头像")
    private String avatarUrl;

    /** 观看时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "观看时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date viewedAt;

    /** 观看时长 */
    @Excel(name = "观看时长")
    private Long viewDuration;

    /** 观看总时长 */
    @Excel(name = "观看总时长")
    private Long viewAllDuration;

    public void setViewId(Long viewId)
    {
        this.viewId = viewId;
    }

    public Long getViewId()
    {
        return viewId;
    }
    public void setStuId(String stuId)
    {
        this.stuId = stuId;
    }

    public String getStuId()
    {
        return stuId;
    }
    public void setTeachingId(Long teachingId)
    {
        this.teachingId = teachingId;
    }

    public Long getTeachingId()
    {
        return teachingId;
    }
    public void setViewedAt(Date viewedAt)
    {
        this.viewedAt = viewedAt;
    }

    public Date getViewedAt()
    {
        return viewedAt;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setViewDuration(Long viewDuration)
    {
        this.viewDuration = viewDuration;
    }

    public Long getViewDuration()
    {
        return viewDuration;
    }

    public Long getViewAllDuration() {
        return viewAllDuration;
    }

    public void setViewAllDuration(Long viewAllDuration) {
        this.viewAllDuration = viewAllDuration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("viewId", getViewId())
            .append("stuId", getStuId())
            .append("teachingId", getTeachingId())
            .append("viewedAt", getViewedAt())
            .append("viewDuration", getViewDuration())
            .append("stuName", getStuName())
            .append("avatarUrl" ,getAvatarUrl())
            .append("ViewAllDuration" ,getViewAllDuration())
            .toString();
    }
}
