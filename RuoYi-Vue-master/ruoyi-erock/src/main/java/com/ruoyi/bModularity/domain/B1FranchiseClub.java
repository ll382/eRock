package com.ruoyi.bModularity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 球队、社团训练对象 b1_franchise_club
 * 
 * @author houq
 * @date 2024-03-27
 */
public class B1FranchiseClub extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 训练ID */
    private Long tcId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String stuId;

    /** 训练URL */
    @Excel(name = "训练URL")
    private String tcUrl;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tcTime;

    /** 训练标题 */
    @Excel(name = "训练标题")
    private String tcTitle;

    /** 训练地点 */
    @Excel(name = "训练地点")
    private String tcPlace;

    /** B1 资源信息 */
    private List<B1MassSource> b1MassSourceList;

    public void setTcId(Long tcId) 
    {
        this.tcId = tcId;
    }

    public Long getTcId() 
    {
        return tcId;
    }
    public void setEnumId(Long enumId) 
    {
        this.enumId = enumId;
    }

    public Long getEnumId() 
    {
        return enumId;
    }
    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
    {
        return stuId;
    }
    public void setTcUrl(String tcUrl) 
    {
        this.tcUrl = tcUrl;
    }

    public String getTcUrl() 
    {
        return tcUrl;
    }
    public void setTcTime(Date tcTime) 
    {
        this.tcTime = tcTime;
    }

    public Date getTcTime() 
    {
        return tcTime;
    }
    public void setTcTitle(String tcTitle) 
    {
        this.tcTitle = tcTitle;
    }

    public String getTcTitle() 
    {
        return tcTitle;
    }
    public void setTcPlace(String tcPlace) 
    {
        this.tcPlace = tcPlace;
    }

    public String getTcPlace() 
    {
        return tcPlace;
    }

    public List<B1MassSource> getB1MassSourceList()
    {
        return b1MassSourceList;
    }

    public void setB1MassSourceList(List<B1MassSource> b1MassSourceList)
    {
        this.b1MassSourceList = b1MassSourceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tcId", getTcId())
            .append("enumId", getEnumId())
            .append("stuId", getStuId())
            .append("tcUrl", getTcUrl())
            .append("tcTime", getTcTime())
            .append("tcTitle", getTcTitle())
            .append("tcPlace", getTcPlace())
            .append("b1MassSourceList", getB1MassSourceList())
            .toString();
    }
}
