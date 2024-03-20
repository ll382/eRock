package com.ruoyi.views.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生成绩视图对象 student_course_grades
 * 
 * @author ljy
 * @date 2024-03-20
 */
public class StudentCourseGrades extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 专业班级名 */
    @Excel(name = "专业班级名")
    private String className;

    /** 运球分数 */
    @Excel(name = "运球分数")
    private BigDecimal msDribble;

    /** EROCK评分 */
    @Excel(name = "EROCK评分")
    private BigDecimal msScore;

    /** 投篮分数 */
    @Excel(name = "投篮分数")
    private BigDecimal msShooting;

    /** 开课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crDate;

    /** 课题内容 */
    @Excel(name = "课题内容")
    private String crMain;

    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teaName;

    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setMsDribble(BigDecimal msDribble) 
    {
        this.msDribble = msDribble;
    }

    public BigDecimal getMsDribble() 
    {
        return msDribble;
    }
    public void setMsScore(BigDecimal msScore) 
    {
        this.msScore = msScore;
    }

    public BigDecimal getMsScore() 
    {
        return msScore;
    }
    public void setMsShooting(BigDecimal msShooting) 
    {
        this.msShooting = msShooting;
    }

    public BigDecimal getMsShooting() 
    {
        return msShooting;
    }
    public void setCrDate(Date crDate) 
    {
        this.crDate = crDate;
    }

    public Date getCrDate() 
    {
        return crDate;
    }
    public void setCrMain(String crMain) 
    {
        this.crMain = crMain;
    }

    public String getCrMain() 
    {
        return crMain;
    }
    public void setTeaName(String teaName) 
    {
        this.teaName = teaName;
    }

    public String getTeaName() 
    {
        return teaName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("stuName", getStuName())
            .append("className", getClassName())
            .append("msDribble", getMsDribble())
            .append("msScore", getMsScore())
            .append("msShooting", getMsShooting())
            .append("crDate", getCrDate())
            .append("crMain", getCrMain())
            .append("teaName", getTeaName())
            .toString();
    }
}
