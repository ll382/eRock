package com.ruoyi.InClassModule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 合作学习 学生互评对象 a2_student_evaluate
 *
 * @author heye
 * @date 2024-04-11
 */
public class A2StudentEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生互评ID */
    private Long seId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 被评学生学号 */
    @Excel(name = "被评学生学号")
    private String esId;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private String eeType;

    /** 参评学生学号 */
    @Excel(name = "参评学生学号")
    private String peId;

    public void setSeId(Long seId)
    {
        this.seId = seId;
    }

    public Long getSeId()
    {
        return seId;
    }
    public void setCrId(Long crId)
    {
        this.crId = crId; 
    }

    public Long getCrId()
    {
        return crId;
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
    public void setEsId(String esId)
    {
        this.esId = esId;
    }

    public String getEsId()
    {
        return esId;
    }
    public void setEeType(String eeType)
    {
        this.eeType = eeType;
    }

    public String getEeType()
    {
        return eeType;
    }
    public void setPeId(String peId)
    {
        this.peId = peId;
    }

    public String getPeId()
    {
        return peId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seId", getSeId())
            .append("crId", getCrId())
            .append("stuId", getStuId())
            .append("esId", getEsId())
            .append("eeType", getEeType())
            .append("peId", getPeId())
            .toString();
    }
}
