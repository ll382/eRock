package com.ruoyi.core.domain.vo;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

public class StudentModuleScore {

    private static final long serialVersionUID = 1L;

    private Long stuId;
    private String stuName;
    private BigDecimal avsScore;


    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public BigDecimal getAvsScore() {
        return avsScore;
    }

    public void setAvsScore(BigDecimal avsScore) {
        this.avsScore = avsScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("stuId", getStuId())
                .append("stuName", getStuName())
                .append("avsScore", getAvsScore())
                .toString();
    }
}
