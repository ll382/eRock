package com.ruoyi.afterClassModel.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A3 体能训练周对象 a3_physical_training
 *
 * @author heye
 * @date 2024-04-15
 */
public class A3PhysicalTraining extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 训练周ID */
    private Long phtrId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private Long teaId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date phtrStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date phtrEndTime;

    /** 周任务名称 */
    @Excel(name = "周任务名称")
    private String phtrName;

    /** 周任务描述 */
    @Excel(name = "周任务描述")
    private String phtrDescribe;

    /** A3 学生周训资源信息 */
    private List<A3WeeklyTraining> a3WeeklyTrainingList;

    public void setPhtrId(Long phtrId)
    {
        this.phtrId = phtrId;
    }

    public Long getPhtrId()
    {
        return phtrId;
    }
    public void setTeaId(Long teaId)
    {
        this.teaId = teaId;
        super.setTeaId(teaId);
    }

    public Long getTeaId()
    {
        return teaId;
    }
    public void setPhtrStartTime(Date phtrStartTime)
    {
        this.phtrStartTime = phtrStartTime;
    }

    public Date getPhtrStartTime()
    {
        return phtrStartTime;
    }
    public void setPhtrEndTime(Date phtrEndTime)
    {
        this.phtrEndTime = phtrEndTime;
    }

    public Date getPhtrEndTime()
    {
        return phtrEndTime;
    }
    public void setPhtrName(String phtrName)
    {
        this.phtrName = phtrName;
    }

    public String getPhtrName()
    {
        return phtrName;
    }
    public void setPhtrDescribe(String phtrDescribe)
    {
        this.phtrDescribe = phtrDescribe;
    }

    public String getPhtrDescribe()
    {
        return phtrDescribe;
    }

    public List<A3WeeklyTraining> getA3WeeklyTrainingList()
    {
        return a3WeeklyTrainingList;
    }

    public void setA3WeeklyTrainingList(List<A3WeeklyTraining> a3WeeklyTrainingList)
    {
        this.a3WeeklyTrainingList = a3WeeklyTrainingList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phtrId", getPhtrId())
            .append("teaId", getTeaId())
            .append("phtrStartTime", getPhtrStartTime())
            .append("phtrEndTime", getPhtrEndTime())
            .append("phtrName", getPhtrName())
            .append("phtrDescribe", getPhtrDescribe())
            .append("a3WeeklyTrainingList", getA3WeeklyTrainingList())
            .toString();
    }
}
