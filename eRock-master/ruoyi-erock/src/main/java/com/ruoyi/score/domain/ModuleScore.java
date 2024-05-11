package com.ruoyi.score.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 模块分数对象 module_score
 * 
 * @author houq
 * @date 2024-04-22
 */
public class ModuleScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模块ID */
    private Long modId;

    /** 总分ID */
    @Excel(name = "总分ID")
    private Long tsId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 成绩 */
    @Excel(name = "成绩")
    private BigDecimal avsScore;

    public ModuleScore() {
    }

    public ModuleScore(Long modId, Long tsId, Long enumId, BigDecimal avsScore) {
        this.modId = modId;
        this.tsId = tsId;
        this.enumId = enumId;
        this.avsScore = avsScore;
    }

    public ModuleScore(Long tsId) {
        this.tsId = tsId;
    }

    public void setModId(Long modId)
    {
        this.modId = modId;
    }

    public Long getModId() 
    {
        return modId;
    }
    public void setTsId(Long tsId) 
    {
        this.tsId = tsId;
    }

    public Long getTsId() 
    {
        return tsId;
    }
    public void setEnumId(Long enumId) 
    {
        this.enumId = enumId;
    }

    public Long getEnumId() 
    {
        return enumId;
    }
    public void setAvsScore(BigDecimal avsScore) 
    {
        this.avsScore = avsScore;
    }

    public BigDecimal getAvsScore() 
    {
        return avsScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modId", getModId())
            .append("tsId", getTsId())
            .append("enumId", getEnumId())
            .append("avsScore", getAvsScore())
            .toString();
    }
}
