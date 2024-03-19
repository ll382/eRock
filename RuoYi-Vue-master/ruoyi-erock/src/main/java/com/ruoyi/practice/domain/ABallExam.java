package com.ruoyi.practice.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投篮运球表对象 a_ball_exam
 * 
 * @author houq
 * @date 2024-03-19
 */
public class ABallExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测试ID */
    private Long beId;

    /** 评分ID */
    @Excel(name = "评分ID")
    private Long msId;

    /** 稳定性 */
    @Excel(name = "稳定性")
    private BigDecimal driStability;

    /** 力量 */
    @Excel(name = "力量")
    private BigDecimal driPower;

    /** 速度 */
    @Excel(name = "速度")
    private BigDecimal driSpeed;

    /** 分析 */
    @Excel(name = "分析")
    private String driAnalysis;

    /** 弧线 */
    @Excel(name = "弧线")
    private BigDecimal shoArc;

    /** 后旋 */
    @Excel(name = "后旋")
    private BigDecimal shoSpinner;

    /** 偏角 */
    @Excel(name = "偏角")
    private BigDecimal shoAngle;

    /** 评价 */
    @Excel(name = "评价")
    private String shoAnalysis;

    public void setBeId(Long beId) 
    {
        this.beId = beId;
    }

    public Long getBeId() 
    {
        return beId;
    }
    public void setMsId(Long msId) 
    {
        this.msId = msId;
    }

    public Long getMsId() 
    {
        return msId;
    }
    public void setDriStability(BigDecimal driStability) 
    {
        this.driStability = driStability;
    }

    public BigDecimal getDriStability() 
    {
        return driStability;
    }
    public void setDriPower(BigDecimal driPower) 
    {
        this.driPower = driPower;
    }

    public BigDecimal getDriPower() 
    {
        return driPower;
    }
    public void setDriSpeed(BigDecimal driSpeed) 
    {
        this.driSpeed = driSpeed;
    }

    public BigDecimal getDriSpeed() 
    {
        return driSpeed;
    }
    public void setDriAnalysis(String driAnalysis) 
    {
        this.driAnalysis = driAnalysis;
    }

    public String getDriAnalysis() 
    {
        return driAnalysis;
    }
    public void setShoArc(BigDecimal shoArc) 
    {
        this.shoArc = shoArc;
    }

    public BigDecimal getShoArc() 
    {
        return shoArc;
    }
    public void setShoSpinner(BigDecimal shoSpinner) 
    {
        this.shoSpinner = shoSpinner;
    }

    public BigDecimal getShoSpinner() 
    {
        return shoSpinner;
    }
    public void setShoAngle(BigDecimal shoAngle) 
    {
        this.shoAngle = shoAngle;
    }

    public BigDecimal getShoAngle() 
    {
        return shoAngle;
    }
    public void setShoAnalysis(String shoAnalysis) 
    {
        this.shoAnalysis = shoAnalysis;
    }

    public String getShoAnalysis() 
    {
        return shoAnalysis;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("beId", getBeId())
            .append("msId", getMsId())
            .append("driStability", getDriStability())
            .append("driPower", getDriPower())
            .append("driSpeed", getDriSpeed())
            .append("driAnalysis", getDriAnalysis())
            .append("shoArc", getShoArc())
            .append("shoSpinner", getShoSpinner())
            .append("shoAngle", getShoAngle())
            .append("shoAnalysis", getShoAnalysis())
            .toString();
    }
}
