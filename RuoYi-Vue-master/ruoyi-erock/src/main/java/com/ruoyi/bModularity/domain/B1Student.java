package com.ruoyi.bModularity.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class B1Student extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long stuId;
	private Long ggId;
	private Long classId;
	private String stuName;

	@Override
	public Long getStuId() {
		return stuId;
	}

	@Override
	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	@Override
	public Long getGgId() {
		return ggId;
	}

	@Override
	public void setGgId(Long ggId) {
		this.ggId = ggId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	@Override
	public String toString() {
		return "\nB1Student{" +
				"stuId=" + stuId +
				", ggId=" + ggId +
				", classId=" + classId +
				", stuName='" + stuName + '\'' +
				'}';
	}
}
