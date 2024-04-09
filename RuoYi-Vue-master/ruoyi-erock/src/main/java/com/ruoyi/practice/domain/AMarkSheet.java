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
 * @date 2024-04-09
 */
public class AMarkSheet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评分ID */
    private Long msId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long etId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private Long teaId;

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

    /** erock评分 */
    @Excel(name = "erock评分")
    private BigDecimal msScore;

    /** 技能数值1 */
    @Excel(name = "技能数值1")
    private BigDecimal ms1;

    /** 技能数值2 */
    @Excel(name = "技能数值2")
    private BigDecimal ms2;

    /** 技能数值3 */
    @Excel(name = "技能数值3")
    private BigDecimal ms3;

    /** 运动处方 */
    @Excel(name = "运动处方")
    private String msClass;

    /** erock截图 */
    @Excel(name = "erock截图")
    private String msImg;

    /** 练习资源表信息 */
    private List<AExerciseResource> aExerciseResourceList;

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
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setTeaId(Long teaId) 
    {
        this.teaId = teaId;
    }

    public Long getTeaId() 
    {
        return teaId;
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
    public void setMsScore(BigDecimal msScore) 
    {
        this.msScore = msScore;
    }

    public BigDecimal getMsScore() 
    {
        return msScore;
    }
    public void setMs1(BigDecimal ms1) 
    {
        this.ms1 = ms1;
    }

    public BigDecimal getMs1() 
    {
        return ms1;
    }
    public void setMs2(BigDecimal ms2) 
    {
        this.ms2 = ms2;
    }

    public BigDecimal getMs2() 
    {
        return ms2;
    }
    public void setMs3(BigDecimal ms3) 
    {
        this.ms3 = ms3;
    }

    public BigDecimal getMs3() 
    {
        return ms3;
    }
    public void setMsClass(String msClass) 
    {
        this.msClass = msClass;
    }

    public String getMsClass() 
    {
        return msClass;
    }
    public void setMsImg(String msImg) 
    {
        this.msImg = msImg;
    }

    public String getMsImg() 
    {
        return msImg;
    }

    public List<AExerciseResource> getAExerciseResourceList()
    {
        return aExerciseResourceList;
    }

    public void setAExerciseResourceList(List<AExerciseResource> aExerciseResourceList)
    {
        this.aExerciseResourceList = aExerciseResourceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msId", getMsId())
            .append("etId", getEtId())
            .append("stuId", getStuId())
            .append("teaId", getTeaId())
            .append("msTime", getMsTime())
            .append("msDribble", getMsDribble())
            .append("msShooting", getMsShooting())
            .append("msScore", getMsScore())
            .append("ms1", getMs1())
            .append("ms2", getMs2())
            .append("ms3", getMs3())
            .append("msClass", getMsClass())
            .append("msImg", getMsImg())
            .append("aExerciseResourceList", getAExerciseResourceList())
            .toString();
    }
}
