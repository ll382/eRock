package com.ruoyi.match.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.practice.domain.AExerciseTask;

/**
 * 课堂记录对象 class_register
 *
 * @author houq
 * @date 2024-04-08
 */
public class ClassRegister extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课堂ID */
    private Long crId;

    /** 学期ID */
    @Excel(name = "学期ID")
    private Long semesterId;

    /** 开课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crDate;

    /** 课题内容 */
    @Excel(name = "课题内容")
    private String crMain;

    /** 开课时长 */
    @Excel(name = "开课时长")
    private BigDecimal crDuration;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long classId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 练习、测试任务表信息 */
    private List<AExerciseTask> aExerciseTaskList;

    /** C 比赛记录信息 */
    private List<CompetitionRecord> competitionRecordList;

    public void setCrId(Long crId)
    {
        this.crId = crId;
    }

    public Long getCrId()
    {
        return crId;
    }
    public void setSemesterId(Long semesterId)
    {
        this.semesterId = semesterId;
    }

    public Long getSemesterId()
    {
        return semesterId;
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
    public void setCrDuration(BigDecimal crDuration)
    {
        this.crDuration = crDuration;
    }

    public BigDecimal getCrDuration()
    {
        return crDuration;
    }
    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }

    public List<CompetitionRecord> getCompetitionRecordList()
    {
        return competitionRecordList;
    }

    public void setCompetitionRecordList(List<CompetitionRecord> competitionRecordList)
    {
        this.competitionRecordList = competitionRecordList;
    }

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public List<AExerciseTask> getaExerciseTaskList() {
        return aExerciseTaskList;
    }

    public void setaExerciseTaskList(List<AExerciseTask> aExerciseTaskList) {
        this.aExerciseTaskList = aExerciseTaskList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("crId", getCrId())
            .append("semesterId", getSemesterId())
            .append("crDate", getCrDate())
            .append("crMain", getCrMain())
            .append("crDuration", getCrDuration())
            .append("classId", getClassId())
            .append("competitionRecordList", getCompetitionRecordList())
            .toString();
    }
}
