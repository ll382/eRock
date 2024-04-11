package com.ruoyi.InClassModule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 合作学习 助教评价
对象 a2_teaching_assistant
 * 
 * @author heye
 * @date 2024-04-11
 */
public class A2TeachingAssistant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 助教评价ID */
    private Long taId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private String taType;

    /** 评价者 */
    @Excel(name = "评价者")
    private Long taStu;

    public void setTaId(Long taId) 
    {
        this.taId = taId;
    }

    public Long getTaId() 
    {
        return taId;
    }
    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
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
    public void setTaType(String taType) 
    {
        this.taType = taType;
    }

    public String getTaType() 
    {
        return taType;
    }
    public void setTaStu(Long taStu) 
    {
        this.taStu = taStu;
    }

    public Long getTaStu() 
    {
        return taStu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taId", getTaId())
            .append("stuId", getStuId())
            .append("crId", getCrId())
            .append("taType", getTaType())
            .append("taStu", getTaStu())
            .toString();
    }
}
