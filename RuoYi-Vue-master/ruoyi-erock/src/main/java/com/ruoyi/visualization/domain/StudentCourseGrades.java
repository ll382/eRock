package com.ruoyi.visualization.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * PC段首页对象 student_course_grades
 * 
 * @author ljy
 * @date 2024-03-22
 */
public class StudentCourseGrades extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 专业班级名 */
    @Excel(name = "专业班级名")
    private String className;

    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teaName;

    /** 运球分数 */
    @Excel(name = "运球分数")
    private BigDecimal msDribble;

    /** 投篮分数 */
    @Excel(name = "投篮分数")
    private BigDecimal msShooting;

    /** 开课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crDate;


    /** 课题内容 */
    @Excel(name = "课题内容")
    private String crMain;

    /** 第几节课 */
    @Excel(name = "第几节课")
    private String whichClass;

    /** 时辰 */
    @Excel(name = "时辰")
    private String time;

    private List<BigDecimal> Score;



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
    public void setTeaName(String teaName) 
    {
        this.teaName = teaName;
    }

    public String getTeaName() 
    {
        return teaName;
    }
    public void setMsDribble(BigDecimal msDribble) 
    {
        this.msDribble = msDribble;
    }

    public BigDecimal getMsDribble() 
    {
        return msDribble;
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

    public String getWhichClass() {
        return whichClass;
    }

    public void setWhichClass(String whichClass) {
        this.whichClass = whichClass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<BigDecimal> getScore() {
        return Score;
    }

    public void setScore(List<BigDecimal> score) {
        Score = score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuName", getStuName())
            .append("className", getClassName())
            .append("teaName", getTeaName())
            .append("msDribble", getMsDribble())
            .append("msShooting", getMsShooting())
            .append("crDate", getCrDate())
            .append("crMain", getCrMain())
            .append("whichClass", getWhichClass())
            .append("Time", getTime())
            .append("Score", getScore())
            .toString();
    }
}
