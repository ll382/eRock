package com.ruoyi.InClassModule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 合作学习 教师评价对象 a2_teaching_assistant_evaluation
 *
 * @author heye
 * @date 2024-04-11
 */
public class A2TeachingAssistantEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师评价ID */
    private Long taeId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private Long teaId;

    /** 小组ID */
    @Excel(name = "小组ID")
    private Long ggId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private String taeType;

    public void setTaeId(Long taeId)
    {
        this.taeId = taeId;
    }

    public Long getTaeId()
    {
        return taeId;
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
    public void setGgId(Long ggId)
    {
        this.ggId = ggId;
        super.setGgId(ggId);
    }

    public Long getGgId()
    {
        return ggId;
    }
    public void setCrId(Long crId)
    {
        this.crId = crId;
    }

    public Long getCrId()
    {
        return crId;
    }
    public void setTaeType(String taeType)
    {
        this.taeType = taeType;
    }

    public String getTaeType()
    {
        return taeType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taeId", getTaeId())
            .append("teaId", getTeaId())
            .append("ggId", getGgId())
            .append("crId", getCrId())
            .append("taeType", getTaeType())
            .toString();
    }
}
