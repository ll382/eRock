package com.ruoyi.InClassModule.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A2 A3体能测试对象 a2_a3_physical_test
 *
 * @author heye
 * @date 2024-04-14
 */
public class A2A3PhysicalTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long phyId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private Long teaId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String phyName;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String phyDescript;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date phyTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date phyEndTime;

    /** A2 A3 体能成绩信息 */
    private List<A2A3PhysicaScore> a2A3PhysicaScoreList;

    public void setPhyId(Long phyId)
    {
        this.phyId = phyId;
    }

    public Long getPhyId()
    {
        return phyId;
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
    public void setEnumId(Long enumId)
    {
        this.enumId = enumId;
    }

    public Long getEnumId()
    {
        return enumId;
    }

    public Long getCrId() {
        return crId;
    }

    public void setCrId(Long crId) {
        this.crId = crId;
    }

    public void setPhyName(String phyName)
    {
        this.phyName = phyName;
    }

    public String getPhyName()
    {
        return phyName;
    }
    public void setPhyDescript(String phyDescript)
    {
        this.phyDescript = phyDescript;
    }

    public String getPhyDescript()
    {
        return phyDescript;
    }
    public void setPhyTime(Date phyTime)
    {
        this.phyTime = phyTime;
    }

    public Date getPhyTime()
    {
        return phyTime;
    }
    public void setPhyEndTime(Date phyEndTime)
    {
        this.phyEndTime = phyEndTime;
    }

    public Date getPhyEndTime()
    {
        return phyEndTime;
    }

    public List<A2A3PhysicaScore> getA2A3PhysicaScoreList()
    {
        return a2A3PhysicaScoreList;
    }

    public void setA2A3PhysicaScoreList(List<A2A3PhysicaScore> a2A3PhysicaScoreList)
    {
        this.a2A3PhysicaScoreList = a2A3PhysicaScoreList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phyId", getPhyId())
            .append("teaId", getTeaId())
            .append("enumId", getEnumId())
            .append("phyName", getPhyName())
            .append("phyDescript", getPhyDescript())
            .append("phyTime", getPhyTime())
            .append("phyEndTime", getPhyEndTime())
            .append("a2A3PhysicaScoreList", getA2A3PhysicaScoreList())
            .toString();
    }
}
