package com.ruoyi.match.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 球队参赛对象 c_ballteam
 * 
 * @author heye
 * @date 2024-03-18
 */
public class CBallteam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参赛ID */
    private Long balId;

    /** 记录ID */
    @Excel(name = "记录ID")
    private Long ccRId;

    /** 小组ID */
    @Excel(name = "小组ID")
    private Long ggId;

    /** 球队比分 */
    @Excel(name = "球队比分")
    private String balNum;

    public void setBalId(Long balId) 
    {
        this.balId = balId;
    }

    public Long getBalId() 
    {
        return balId;
    }
    public void setCcRId(Long ccRId) 
    {
        this.ccRId = ccRId;
    }

    public Long getCcRId() 
    {
        return ccRId;
    }
    public void setGgId(Long ggId) 
    {
        this.ggId = ggId;
    }

    public Long getGgId() 
    {
        return ggId;
    }
    public void setBalNum(String balNum) 
    {
        this.balNum = balNum;
    }

    public String getBalNum() 
    {
        return balNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("balId", getBalId())
            .append("ccRId", getCcRId())
            .append("ggId", getGgId())
            .append("balNum", getBalNum())
            .toString();
    }
}
