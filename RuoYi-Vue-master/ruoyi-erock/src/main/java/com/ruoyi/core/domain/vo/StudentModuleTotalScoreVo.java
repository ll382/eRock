package com.ruoyi.core.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.List;

/**
 * 学生各个模块的期末成绩
 */
public class StudentModuleTotalScoreVo {
    /** 学号 */
    private Long stuId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 期末成绩 */
    @Excel(name = "期末成绩")
    private BigDecimal epScore;

    /** 成绩 */
    @Excel(name = "成绩")
    private List<BigDecimal> avsScore;

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

    public BigDecimal getEpScore() {
        return epScore;
    }

    public void setEpScore(BigDecimal epScore) {
        this.epScore = epScore;
    }

    public List<BigDecimal> getAvsScore() {
        return avsScore;
    }

    public void setAvsScore(List<BigDecimal> avsScore) {
        this.avsScore = avsScore;
    }

    @Override
    public String toString() {
        return "StudentModuleTotalScoreVo{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", epScore=" + epScore +
                ", avsScore=" + avsScore +
                '}';
    }
}
