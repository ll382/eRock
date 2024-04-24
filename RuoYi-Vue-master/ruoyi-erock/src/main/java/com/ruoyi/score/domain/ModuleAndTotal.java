package com.ruoyi.score.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class ModuleAndTotal extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * ģ��ID
	 */
	private Long modId;

	/**
	 * �ܷ�ID
	 */
	@Excel(name = "�ܷ�ID")
	private Long tsId;

	/**
	 * ö��ID
	 */
	@Excel(name = "ö��ID")
	private Long enumId;

	/**
	 * �ɼ�
	 */
	@Excel(name = "�ɼ�")
	private BigDecimal avsScore;

	/**
	 * ѧ��ID
	 */
	@Excel(name = "ѧ��ID")
	private Long semesterId;

	/**
	 * ��ĩ�ɼ�
	 */
	@Excel(name = "��ĩ�ɼ�")
	private BigDecimal epScore;

	/**
	 * ѧ��ID
	 */
	@Excel(name = "ѧ��ID")
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
