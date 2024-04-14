package com.ruoyi.InClassModule.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 A3 体能成绩对象 a2_a3_physica_score
 * 
 * @author heye
 * @date 2024-04-14
 */
public class A2A3PhysicaScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩ID */
    private Long physId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long phyId;

    /** 耐力 */
    @Excel(name = "耐力")
    private BigDecimal physStamina2;

    /** 速度 */
    @Excel(name = "速度")
    private BigDecimal physSpeed2;

    public void setPhysId(Long physId) 
    {
        this.physId = physId;
    }

    public Long getPhysId() 
    {
        return physId;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setPhyId(Long phyId) 
    {
        this.phyId = phyId;
    }

    public Long getPhyId() 
    {
        return phyId;
    }
    public void setPhysStamina2(BigDecimal physStamina2) 
    {
        this.physStamina2 = physStamina2;
    }

    public BigDecimal getPhysStamina2() 
    {
        return physStamina2;
    }
    public void setPhysSpeed2(BigDecimal physSpeed2) 
    {
        this.physSpeed2 = physSpeed2;
    }

    public BigDecimal getPhysSpeed2() 
    {
        return physSpeed2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("physId", getPhysId())
            .append("stuId", getStuId())
            .append("phyId", getPhyId())
            .append("physStamina2", getPhysStamina2())
            .append("physSpeed2", getPhysSpeed2())
            .toString();
    }
}
