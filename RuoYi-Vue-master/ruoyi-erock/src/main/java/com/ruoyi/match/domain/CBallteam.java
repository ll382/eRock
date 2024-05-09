package com.ruoyi.match.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 球队参赛对象 c_ballteam
 *
 * @author heye
 * @date 2024-03-19
 */
public class CBallteam extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 参赛ID
	 */
	private Long balId;

	/**
	 * 记录ID
	 */
	@Excel(name = "记录ID")
	private Long ccRId;

	/**
	 * 小组ID
	 */
	@Excel(name = "小组ID")
	private Long ggId;

	/**
	 * 球队比分
	 */
	@Excel(name = "球队比分")
	private String balNum;

	@Excel(name = "球队名称")
	private String ggName;

	/**
	 * C 球队内人员信息
	 */
	private List<CPersonnelSheet> cPersonnelSheetList;

	public void setBalId(Long balId) {
		this.balId = balId;
	}

	public Long getBalId() {
		return balId;
	}

	public void setCcRId(Long ccRId) {
		this.ccRId = ccRId;
	}

	public Long getCcRId() {
		return ccRId;
	}

	public void setGgId(Long ggId) {
		this.ggId = ggId;
        super.setGgId(ggId);
	}

	public Long getGgId() {
		return ggId;
	}

	public void setBalNum(String balNum) {
		this.balNum = balNum;
	}

	public String getBalNum() {
		return balNum;
	}

	public List<CPersonnelSheet> getcPersonnelSheetList() {
		return cPersonnelSheetList;
	}

	public void setcPersonnelSheetList(List<CPersonnelSheet> cPersonnelSheetList) {
		this.cPersonnelSheetList = cPersonnelSheetList;
	}

	public List<CPersonnelSheet> getCPersonnelSheetList() {
		return cPersonnelSheetList;
	}

	public void setCPersonnelSheetList(List<CPersonnelSheet> cPersonnelSheetList) {
		this.cPersonnelSheetList = cPersonnelSheetList;
	}

	public String getGgName() {
		return ggName;
	}

	public void setGgName(String ggName) {
		this.ggName = ggName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("balId", getBalId())
				.append("ccRId", getCcRId())
				.append("ggId", getGgId())
				.append("balNum", getBalNum())
				.append("cPersonnelSheetList", getCPersonnelSheetList())
				.append("ggName", getGgName())
				.toString();
	}
}
