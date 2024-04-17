package com.ruoyi.afterClassModel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A3 学生周训资源对象 a3_weekly_training
 *
 * @author heye
 * @date 2024-04-15
 */
public class A3WeeklyTraining extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 周训资源ID */
    private Long wetrId;

    /** 训练周ID */
    @Excel(name = "训练周ID")
    private Long phtrId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wetrTime;

    /** 资源URL */
    @Excel(name = "资源URL")
    private String wetrUrl;

    public void setWetrId(Long wetrId)
    {
        this.wetrId = wetrId;
    }

    public Long getWetrId()
    {
        return wetrId;
    }
    public void setPhtrId(Long phtrId)
    {
        this.phtrId = phtrId;
    }

    public Long getPhtrId()
    {
        return phtrId;
    }
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
        super.setStuId(stuId);
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setWetrTime(Date wetrTime)
    {
        this.wetrTime = wetrTime;
    }

    public Date getWetrTime()
    {
        return wetrTime;
    }
    public void setWetrUrl(String wetrUrl)
    {
        this.wetrUrl = wetrUrl;
    }

    public String getWetrUrl()
    {
        return wetrUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wetrId", getWetrId())
            .append("phtrId", getPhtrId())
            .append("stuId", getStuId())
            .append("wetrTime", getWetrTime())
            .append("wetrUrl", getWetrUrl())
            .toString();
    }
}
