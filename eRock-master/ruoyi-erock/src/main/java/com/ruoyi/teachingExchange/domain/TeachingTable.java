package com.ruoyi.teachingExchange.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 线上学习学生线上观看记录表对象 teaching_table
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
    private Long teaId;

    /** 资源url */
    @Excel(name = "资源url")
    private String resourceUrl;

    /** 排序ID */
    @Excel(name = "排序ID")
    private BigDecimal teachingOrder;

    /** 课时ID */
    @Excel(name = "课时ID")
    private Long lesId;

    /** 枚举名称 */
    @Excel(name = "枚举名称")
    private String lesName;

    /** 教学标题 */
    @Excel(name = "教学标题")
    private String teachingTitle;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** A1交流信息 */
    private List<A1Communication> a1CommunicationList;

    /** A1 线上学习 观看记录信息 */
    private List<A1Viewed> a1ViewedList;

    public void setTeachingId(Long teachingId)
    {
        this.teachingId = teachingId;
    }

    public Long getTeachingId()
    {
        return teachingId;
    }
    public void setTeaId(Long teaId)
    {
        this.teaId = teaId;
        super.setTeaId(teaId);
    }

    public Long getTeaId()
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

    public Long getLesId() {
        return lesId;
    }

    public void setLesId(Long lesId) {
        this.lesId = lesId;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public BigDecimal getTeachingOrder() {
        return teachingOrder;
    }

    public void setTeachingOrder(BigDecimal teachingOrder) {
        this.teachingOrder = teachingOrder;
    }

    public String getTeachingTitle() {
        return teachingTitle;
    }

    public void setTeachingTitle(String teachingTitle) {
        this.teachingTitle = teachingTitle;
    }

    public List<A1Communication> getA1CommunicationList()
    {
        return a1CommunicationList;
    }

    public void setA1CommunicationList(List<A1Communication> a1CommunicationList)
    {
        this.a1CommunicationList = a1CommunicationList;
    }

    public List<A1Viewed> getA1ViewedList() {
        return a1ViewedList;
    }

    public void setA1ViewedList(List<A1Viewed> a1ViewedList) {
        this.a1ViewedList = a1ViewedList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teachingId", getTeachingId())
            .append("teaId", getTeaId())
            .append("resourceUrl", getResourceUrl())
            .append("createdAt", getCreatedAt())
            .append("a1CommunicationList", getA1CommunicationList())
            .append("a1ViewedList", getA1ViewedList())
            .append("lesId", getLesId())
            .append("lesName", getLesName())
            .append("teachingTitle", getTeachingTitle())
            .append("teachingOrder", getTeachingOrder())
            .toString();
    }
}
