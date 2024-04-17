package com.ruoyi.dModularity.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * D2 证书表对象 d2_certificate
 * 
 * @author houq
 * @date 2024-04-16
 */
public class D2Certificate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 证书ID */
    private Long certificateId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private Long teaId;

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String certificateName;

    /** 证书审核 */
    @Excel(name = "证书审核")
    private Long audit;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** D2 资源信息 */
    private List<D2Resource> d2ResourceList;

    public void setCertificateId(Long certificateId) 
    {
        this.certificateId = certificateId;
    }

    public Long getCertificateId() 
    {
        return certificateId;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Long getTeaId() {
        return teaId;
    }

    public void setTeaId(Long teaId) {
        this.teaId = teaId;
    }

    public void setCertificateName(String certificateName)
    {
        this.certificateName = certificateName;
    }

    public String getCertificateName() 
    {
        return certificateName;
    }
    public void setAudit(Long audit) 
    {
        this.audit = audit;
    }

    public Long getAudit() 
    {
        return audit;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public List<D2Resource> getD2ResourceList()
    {
        return d2ResourceList;
    }

    public void setD2ResourceList(List<D2Resource> d2ResourceList)
    {
        this.d2ResourceList = d2ResourceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("certificateId", getCertificateId())
            .append("stuId", getStuId())
            .append("teaId", getTeaId())
            .append("certificateName", getCertificateName())
            .append("audit", getAudit())
            .append("uploadTime", getUploadTime())
            .append("d2ResourceList", getD2ResourceList())
            .toString();
    }
}
