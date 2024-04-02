package com.ruoyi.teachingExchange.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课时单元对象 lesson_units
 * 
 * @author heye
 * @date 2024-04-01
 */
public class LessonUnits extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课时ID */
    private Long lesId;

    /** 课时标题 */
    @Excel(name = "课时标题")
    private String lesName;

    /** 课时顺序ID */
    @Excel(name = "课时顺序ID")
    private Long lesOrderId;

    /** A1 线上学习学生线上观看记录表信息 */
    private List<TeachingTable> teachingTableList;

    public void setLesId(Long lesId) 
    {
        this.lesId = lesId;
    }

    public Long getLesId() 
    {
        return lesId;
    }
    public void setLesName(String lesName) 
    {
        this.lesName = lesName;
    }

    public String getLesName() 
    {
        return lesName;
    }
    public void setLesOrderId(Long lesOrderId) 
    {
        this.lesOrderId = lesOrderId;
    }

    public Long getLesOrderId() 
    {
        return lesOrderId;
    }

    public List<TeachingTable> getTeachingTableList()
    {
        return teachingTableList;
    }

    public void setTeachingTableList(List<TeachingTable> teachingTableList)
    {
        this.teachingTableList = teachingTableList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lesId", getLesId())
            .append("lesName", getLesName())
            .append("lesOrderId", getLesOrderId())
            .append("teachingTableList", getTeachingTableList())
            .toString();
    }
}
