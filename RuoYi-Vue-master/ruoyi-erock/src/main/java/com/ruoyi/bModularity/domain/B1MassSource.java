package com.ruoyi.bModularity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * B1 资源对象 b1_mass_source
 *
 * @author houq
 * @date 2024-03-20
 */
public class B1MassSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    
    /** 资源ID */
    private Long masId;
    
    /** 训练ID */
    @Excel(name = "训练ID")
    private Long tcId;
    
    /** 资源URL */
    @Excel(name = "资源URL")
    private String teasUrl;
    
    public void setMasId(Long masId)
    {
        this.masId = masId;
    }
    
    public Long getMasId()
    {
        return masId;
    }
    public void setTcId(Long tcId)
    {
        this.tcId = tcId;
    }
    
    public Long getTcId()
    {
        return tcId;
    }
    public void setTeasUrl(String teasUrl)
    {
        this.teasUrl = teasUrl;
    }
    
    public String getTeasUrl()
    {
        return teasUrl;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("masId", getMasId())
                .append("tcId", getTcId())
                .append("teasUrl", getTeasUrl())
                .toString();
    }
}
