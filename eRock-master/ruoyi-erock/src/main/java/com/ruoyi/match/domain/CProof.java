package com.ruoyi.match.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * C 佐证对象 c_proof
 * 
 * @author heye
 * @date 2024-03-19
 */
public class CProof extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 佐证ID */
    private Long proofId;

    /** 人员ID */
    @Excel(name = "人员ID")
    private Long psId;

    /** 佐证URL */
    @Excel(name = "佐证URL")
    private String proofUrl;

    public void setProofId(Long proofId) 
    {
        this.proofId = proofId;
    }

    public Long getProofId() 
    {
        return proofId;
    }
    public void setPsId(Long psId) 
    {
        this.psId = psId;
    }

    public Long getPsId() 
    {
        return psId;
    }
    public void setProofUrl(String proofUrl) 
    {
        this.proofUrl = proofUrl;
    }

    public String getProofUrl() 
    {
        return proofUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("proofId", getProofId())
            .append("psId", getPsId())
            .append("proofUrl", getProofUrl())
            .toString();
    }
}
