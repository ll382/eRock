package com.ruoyi.views.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 查询可视化折线图数据对象 line_chart
 * 
 * @author ljy
 * @date 2024-03-21
 */
public class LineChart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    private Long stuId;

    /** EROCK评分 */
    @Excel(name = "EROCK评分")
    private BigDecimal msScore;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 开课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crDate;

    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setMsScore(BigDecimal msScore) 
    {
        this.msScore = msScore;
    }

    public BigDecimal getMsScore() 
    {
        return msScore;
    }
    public void setEnumId(Long enumId) 
    {
        this.enumId = enumId;
    }

    public Long getEnumId() 
    {
        return enumId;
    }
    public void setCrDate(Date crDate) 
    {
        this.crDate = crDate;
    }

    public Date getCrDate() 
    {
        return crDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("msScore", getMsScore())
            .append("enumId", getEnumId())
            .append("crDate", getCrDate())
            .toString();
    }
}
