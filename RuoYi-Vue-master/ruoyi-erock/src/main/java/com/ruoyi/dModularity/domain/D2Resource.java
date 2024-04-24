package com.ruoyi.dModularity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * D2 资源对象 d2_resource
 * 
 * @author houq
 * @date 2024-04-16
 */
public class D2Resource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Long resourceId;

    /** 证书ID */
    @Excel(name = "证书ID")
    private Long certificateId;

    /** 资源url */
    @Excel(name = "资源url")
    private String resourceUrl;

    public void setResourceId(Long resourceId) 
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId() 
    {
        return resourceId;
    }
    public void setCertificateId(Long certificateId) 
    {
        this.certificateId = certificateId;
    }

    public Long getCertificateId() 
    {
        return certificateId;
    }
    public void setResourceUrl(String resourceUrl) 
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl() 
    {
        return resourceUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resourceId", getResourceId())
            .append("certificateId", getCertificateId())
            .append("resourceUrl", getResourceUrl())
            .toString();
    }
}
