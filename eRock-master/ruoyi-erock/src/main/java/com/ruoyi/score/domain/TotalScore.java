package com.ruoyi.score.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 总分对象 total_score
 *
 * @author houq
 * @date 2024-04-22
 */
public class TotalScore extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 总分ID
	 */
	private Long tsId;

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

	public TotalScore() {
	}

	public TotalScore(Long stuId, Long semesterId) {
		this.semesterId = semesterId;
		this.stuId = stuId;
	}

	public TotalScore(Long tsId, Long semesterId, BigDecimal epScore, Long stuId) {
		this.tsId = tsId;
		this.semesterId = semesterId;
		this.epScore = epScore;
		this.stuId = stuId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Long getTsId() {
		return tsId;
	}

	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}

	public Long getSemesterId() {
		return semesterId;
	}

	public void setEpScore(BigDecimal epScore) {
		this.epScore = epScore;
	}

	public BigDecimal getEpScore() {
		return epScore;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public Long getStuId() {
		return stuId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("tsId", getTsId())
				.append("semesterId", getSemesterId())
				.append("epScore", getEpScore())
				.append("stuId", getStuId())
				.toString();
	}
}
