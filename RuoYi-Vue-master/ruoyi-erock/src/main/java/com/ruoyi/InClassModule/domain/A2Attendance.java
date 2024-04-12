package com.ruoyi.InClassModule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 课堂表现classRegistercr         对象 a2_attendance
 *
 * @author heye
 * @date 2024-04-11
 */
public class A2Attendance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考勤ID */
    private Long aaId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 考勤类型 */
    @Excel(name = "考勤类型")
    private String aaType;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private String evaluationType2;

    public void setAaId(Long aaId)
    {
        this.aaId = aaId;
    }

    public Long getAaId()
    {
        return aaId;
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
    public void setCrId(Long crId)
    {
        this.crId = crId;
    }

    public Long getCrId()
    {
        return crId;
    }
    public void setAaType(String aaType)
    {
        this.aaType = aaType;
    }

    public String getAaType()
    {
        return aaType;
    }
    public void setEvaluationType2(String evaluationType2)
    {
        this.evaluationType2 = evaluationType2;
    }

    public String getEvaluationType2()
    {
        return evaluationType2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aaId", getAaId())
            .append("stuId", getStuId())
            .append("crId", getCrId())
            .append("aaType", getAaType())
            .append("evaluationType2", getEvaluationType2())
            .toString();
    }
}
