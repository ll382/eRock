package com.ruoyi.visualization.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * PC段第二页面对象 Histogram_line_chart
 * 
 * @author ljy
 * @date 2024-03-25
 */
public class HistogramLineChart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 运球分数 */
    @Excel(name = "运球分数")
    private BigDecimal msDribble;

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

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
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
    public void setEnumId(Long enumId) 
    {
        this.enumId = enumId;
    }

    public Long getEnumId() 
    {
        return enumId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuName", getStuName())
            .append("msDribble", getMsDribble())
            .append("msShooting", getMsShooting())
            .append("crDate", getCrDate())
            .append("crMain", getCrMain())
            .append("enumId", getEnumId())
            .toString();
    }
}
