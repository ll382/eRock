package com.ruoyi.dModularity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class D2CertificateAuditByStuId extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long stuId;
	private Long teaId;
	private Long certificateId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date uploadTime;
	private Long audit;
	private String certificateName;
	private String resourceUrl;
	private Integer urlNum;

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

	public Long getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Long getAudit() {
		return audit;
	}

	public void setAudit(Long audit) {
		this.audit = audit;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Integer getUrlNum() {
		return urlNum;
	}

	public void setUrlNum(Integer urlNum) {
		this.urlNum = urlNum;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("stuId", getStuId())
				.append("teaId", getTeaId())
				.append("certificateId", getCertificateId())
				.append("uploadTime", getUploadTime())
				.append("audit", getAudit())
				.append("certificateName", getCertificateName())
				.append("resourceUrl", getResourceUrl())
				.append("urlNum", getUrlNum())
				.toString();
	}
}
