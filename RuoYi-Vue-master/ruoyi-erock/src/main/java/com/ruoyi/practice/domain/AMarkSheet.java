package com.ruoyi.practice.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 练习、测试评分表对象 a_mark_sheet
 * 
 * @author houq
 * @date 2024-03-19
 */
public class AMarkSheet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评测ID */
    private Long msId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long etId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private String teaId;

    /** EROCK评分 */
    @Excel(name = "EROCK评分")
    private BigDecimal msScore;

    /** 评分时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评分时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date msTime;

    /** 运球分数 */
    @Excel(name = "运球分数")
    private BigDecimal msDribble;

    /** 投篮分数 */
    @Excel(name = "投篮分数")
    private BigDecimal msShooting;

    /** 投篮运球表信息 */
    private List<ABallExam> aBallExamList;

    public void setMsId(Long msId) 
    {
        this.msId = msId;
    }

    public Long getMsId() 
    {
        return msId;
    }
    public void setEtId(Long etId) 
    {
        this.etId = etId;
    }

    public Long getEtId() 
    {
        return etId;
    }
    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
    {
        return stuId;
    }
    public void setTeaId(String teaId) 
    {
        this.teaId = teaId;
    }

    public String getTeaId() 
    {
        return teaId;
    }
    public void setMsScore(BigDecimal msScore) 
    {
        this.msScore = msScore;
    }

    public BigDecimal getMsScore() 
    {
        return msScore;
    }
    public void setMsTime(Date msTime) 
    {
        this.msTime = msTime;
    }

    public Date getMsTime() 
    {
        return msTime;
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

    public List<ABallExam> getABallExamList()
    {
        return aBallExamList;
    }

    public void setABallExamList(List<ABallExam> aBallExamList)
    {
        this.aBallExamList = aBallExamList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msId", getMsId())
            .append("etId", getEtId())
            .append("stuId", getStuId())
            .append("teaId", getTeaId())
            .append("msScore", getMsScore())
            .append("msTime", getMsTime())
            .append("msDribble", getMsDribble())
            .append("msShooting", getMsShooting())
            .append("aBallExamList", getABallExamList())
            .toString();
    }
}
