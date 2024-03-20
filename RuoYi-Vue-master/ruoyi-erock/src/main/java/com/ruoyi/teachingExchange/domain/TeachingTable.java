package com.ruoyi.teachingExchange.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * A1 线上学习 教学
 对象 teaching_table
 *
 * @author heye
 * @date 2024-03-20
 */
public class TeachingTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教学资源ID */
    private Long teachingId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private String teaId;

    /** 资源url */
    @Excel(name = "资源url")
    private String resourceUrl;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** A1交流信息 */
    private List<A1Communication> a1CommunicationList;

    public void setTeachingId(Long teachingId)
    {
        this.teachingId = teachingId;
    }

    public Long getTeachingId()
    {
        return teachingId;
    }
    public void setTeaId(String teaId)
    {
        this.teaId = teaId;
    }

    public String getTeaId()
    {
        return teaId;
    }
    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public List<A1Communication> getA1CommunicationList()
    {
        return a1CommunicationList;
    }

    public void setA1CommunicationList(List<A1Communication> a1CommunicationList)
    {
        this.a1CommunicationList = a1CommunicationList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teachingId", getTeachingId())
            .append("teaId", getTeaId())
            .append("resourceUrl", getResourceUrl())
            .append("createdAt", getCreatedAt())
            .append("a1CommunicationList", getA1CommunicationList())
            .toString();
    }
}
