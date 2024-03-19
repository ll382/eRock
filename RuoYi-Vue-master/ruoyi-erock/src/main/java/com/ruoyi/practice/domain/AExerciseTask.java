package com.ruoyi.practice.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 练习、测试任务表对象 a_exercise_task
 * 
 * @author houq
 * @date 2024-03-19
 */
public class AExerciseTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long etId;

    /** 课堂ID */
    @Excel(name = "课堂ID")
    private Long crId;

    /** 老师工号 */
    @Excel(name = "老师工号")
    private String teaId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String etName;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String etDescription;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date etCreateTime;

    /** 练习、测试评分表信息 */
    private List<AMarkSheet> aMarkSheetList;

    public void setEtId(Long etId) 
    {
        this.etId = etId;
    }

    public Long getEtId() 
    {
        return etId;
    }
    public void setCrId(Long crId) 
    {
        this.crId = crId;
    }

    public Long getCrId() 
    {
        return crId;
    }
    public void setTeaId(String teaId) 
    {
        this.teaId = teaId;
    }

    public String getTeaId() 
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
    public void setEtName(String etName) 
    {
        this.etName = etName;
    }

    public String getEtName() 
    {
        return etName;
    }
    public void setEtDescription(String etDescription) 
    {
        this.etDescription = etDescription;
    }

    public String getEtDescription() 
    {
        return etDescription;
    }
    public void setEtCreateTime(Date etCreateTime) 
    {
        this.etCreateTime = etCreateTime;
    }

    public Date getEtCreateTime() 
    {
        return etCreateTime;
    }

    public List<AMarkSheet> getAMarkSheetList()
    {
        return aMarkSheetList;
    }

    public void setAMarkSheetList(List<AMarkSheet> aMarkSheetList)
    {
        this.aMarkSheetList = aMarkSheetList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("etId", getEtId())
            .append("crId", getCrId())
            .append("teaId", getTeaId())
            .append("enumId", getEnumId())
            .append("etName", getEtName())
            .append("etDescription", getEtDescription())
            .append("etCreateTime", getEtCreateTime())
            .append("aMarkSheetList", getAMarkSheetList())
            .toString();
    }
}
