package com.ruoyi.match.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课外赛上传资源
 */
public class KwUploadResource extends BaseEntity {
	private static final long serialVersionUID = 1L;


	/**
	 * 比赛规格
	 */
	@Excel(name = "比赛规格id")
	private Integer ccRSpeciId;

	/**
	 * 比赛规格
	 */
	@Excel(name = "比赛规格")
	private String ccRSpeci;

	/**
	 * 职位ID
	 */
	@Excel(name = "职位ID")
	private Long jobId;

	/**
	 * 学生学号
	 */
	@Excel(name = "学生学号")
	private Long stuId;

	/**
	 * 材料url
	 */
	@Excel(name = "材料url")
	private String psUrl;

	public KwUploadResource() {
	}

	public KwUploadResource(String ccRSpeci, Long jobId, Long stuId, String psUrl) {
		this.ccRSpeci = ccRSpeci;
		this.jobId = jobId;
		this.stuId = stuId;
		this.psUrl = psUrl;
	}

	public Integer getCcRSpeciId() {
		return ccRSpeciId;
	}

	public void setCcRSpeciId(Integer ccRSpeciId) {
		this.ccRSpeciId = ccRSpeciId;
	}

	public String getCcRSpeci() {
		return ccRSpeci;
	}

	public void setCcRSpeci(String ccRSpeci) {
		this.ccRSpeci = ccRSpeci;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@Override
	public Long getStuId() {
		return stuId;
	}

	@Override
	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getPsUrl() {
		return psUrl;
	}

	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}

	@Override
	public String toString() {
		return "KwUploadResource{" +
				"ccRSpeciId='" + ccRSpeciId + '\'' +
				", ccRSpeci='" + ccRSpeci + '\'' +
				", jobId=" + jobId +
				", stuId=" + stuId +
				", psUrl='" + psUrl + '\'' +
				'}';
	}
}
