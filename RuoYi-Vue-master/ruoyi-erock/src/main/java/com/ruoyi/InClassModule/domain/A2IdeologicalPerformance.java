package com.ruoyi.InClassModule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2  思政教育 思政现对象 a2_ideological_performance
 * 
 * @author heye
 * @date 2024-04-11
 */
public class A2IdeologicalPerformance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 思政表现ID */
    private Long ipId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 思政表现类型 */
    @Excel(name = "思政表现类型")
    private String ipType;

    public void setIpId(Long ipId) 
    {
        this.ipId = ipId;
    }

    public Long getIpId() 
    {
        return ipId;
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
    public void setIpType(String ipType) 
    {
        this.ipType = ipType;
    }

    public String getIpType() 
    {
        return ipType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ipId", getIpId())
            .append("stuId", getStuId())
            .append("crId", getCrId())
            .append("ipType", getIpType())
            .toString();
    }
}
