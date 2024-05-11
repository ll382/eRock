package com.ruoyi.core.domain.dto;

import com.ruoyi.common.annotation.Excel;

public class StudentDTO {

    /** 学号 */
    private Long stuId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long classId;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "stuId=" + stuId +
                ", classId=" + classId +
                '}';
    }
}
