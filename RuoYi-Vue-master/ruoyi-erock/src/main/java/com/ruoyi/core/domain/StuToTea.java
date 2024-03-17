package com.ruoyi.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多个学生对多个老师对象 stu_to_tea
 *
 * @author heye
 * @date 2024-03-17
 */
public class StuToTea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    private Long stuId;

    /** 老师工号 */
    private Long teaId;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("teaId", getTeaId())
            .toString();
    }
}
