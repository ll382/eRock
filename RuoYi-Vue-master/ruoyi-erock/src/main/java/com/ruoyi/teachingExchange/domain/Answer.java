package com.ruoyi.teachingExchange.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 知识测试 学生成绩对象 a1_answer
 *
 * @author heye
 * @date 2024-04-06
 */
public class Answer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 答题ID */
    private Long ansId;

    /** 知识测试任务 */
    @Excel(name = "知识测试任务")
    private Long taskId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 回答次数 */
    @Excel(name = "回答次数")
    private Long ansResponse;

    /** 正确次数 */
    @Excel(name = "正确次数")
    private Long ansApos;

    /** 二次计算分数 */
    @Excel(name = "二次计算分数")
    private Double ansScore;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ansTime;

    /** 学生图片 */
    @Excel(name = "学生图片")
    private String stuImg;

    public void setAnsId(Long ansId)
    {
        this.ansId = ansId;
    }

    public Long getAnsId()
    {
        return ansId;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setStuId(String stuId)
    {
        this.stuId = stuId;
    }

    public String getStuId()
    {
        return stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setAnsResponse(Long ansResponse)
    {
        this.ansResponse = ansResponse;
    }

    public Long getAnsResponse()
    {
        return ansResponse;
    }
    public void setAnsApos(Long ansApos)
    {
        this.ansApos = ansApos;
    }

    public Long getAnsApos()
    {
        return ansApos;
    }

    public Double getAnsScore() {
        return ansScore;
    }

    public void setAnsScore(Double ansScore) {
        this.ansScore = ansScore;
    }

    public String getStuImg() {
        return stuImg;
    }

    public void setStuImg(String stuImg) {
        this.stuImg = stuImg;
    }

    public void setAnsTime(Date ansTime)
    {
        this.ansTime = ansTime;
    }

    public Date getAnsTime()
    {
        return ansTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ansId", getAnsId())
            .append("taskId", getTaskId())
            .append("stuId", getStuId())
            .append("ansResponse", getAnsResponse())
            .append("ansApos", getAnsApos())
            .append("ansTime", getAnsTime())
            .append( "stuName", getStuName())
            .append("ansTime", getAnsScore())
            .toString();
    }
}
