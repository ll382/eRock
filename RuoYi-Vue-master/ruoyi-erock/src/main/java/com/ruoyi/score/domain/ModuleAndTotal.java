package com.ruoyi.score.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class ModuleAndTotal extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 模块ID
	 */
	private Long modId;

	/**
	 * 总分ID
	 */
	@Excel(name = "总分ID")
	private Long tsId;

	/**
	 * 枚举ID
	 */
	@Excel(name = "枚举ID")
	private Long enumId;

	/**
	 * 成绩
	 */
	@Excel(name = "成绩")
	private BigDecimal avsScore;

	/**
	 * 学期ID
	 */
	@Excel(name = "学期ID")
	private Long semesterId;

	/**
	 * 期末成绩
	 */
	@Excel(name = "期末成绩")
	private BigDecimal epScore;

	/**
	 * 学生ID
	 */
	@Excel(name = "学生ID")
	private Long stuId;

	public Long getModId() {
		return modId;
	}

	public void setModId(Long modId) {
		this.modId = modId;
	}

	public Long getTsId() {
		return tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Long getEnumId() {
		return enumId;
	}

	public void setEnumId(Long enumId) {
		this.enumId = enumId;
	}

	public BigDecimal getAvsScore() {
		return avsScore;
	}

	public void setAvsScore(BigDecimal avsScore) {
		this.avsScore = avsScore;
	}

	public Long getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}

	public BigDecimal getEpScore() {
		return epScore;
	}

	public void setEpScore(BigDecimal epScore) {
		this.epScore = epScore;
	}

	@Override
	public Long getStuId() {
		return stuId;
	}

	@Override
	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	@Override
	public String toString() {
		return "\nModuleAndTotal{" +
				"modId=" + modId +
				", tsId=" + tsId +
				", enumId=" + enumId +
				", avsScore=" + avsScore +
				", semesterId=" + semesterId +
				", epScore=" + epScore +
				", stuId=" + stuId +
				'}';
	}
}
