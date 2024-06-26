package com.ruoyi.knowledgeQuiz.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.core.domain.Student;
import com.ruoyi.teachingExchange.domain.Answer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 知识测试任务对象 a1_task
 *
 * @author heye
 * @date 2024-04-06
 */
public class A1Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 知识测试任务ID */
    private Long taskId;

    /** 老师外键 */
    @Excel(name = "老师外键")
    private Long teaId;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskDatetime;

    /** 发布标题 */
    @Excel(name = "发布标题")
    private String taskTitle;

    /** 发布简介 */
    @Excel(name = "发布简介")
    private String taskContent;

    /** 任务总题数 */
    @Excel(name = "任务总题数")
    private Integer taskNum;

    /** A1 知识测试 学生成绩信息 */
    private List<Answer> answerList;

    /** A1 已交学生名单 */
    private Long Submitted;

    /** A1 未交学生名单 */
    private Long Unpaid;

    /** A1 未交学生名单 */
    private List<Student> UnpaidList;

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
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
    public void setTaskDatetime(Date taskDatetime)
    {
        this.taskDatetime = taskDatetime;
    }

    public Date getTaskDatetime()
    {
        return taskDatetime;
    }
    public void setTaskTitle(String taskTitle)
    {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle()
    {
        return taskTitle;
    }
    public void setTaskContent(String taskContent)
    {
        this.taskContent = taskContent;
    }

    public String getTaskContent()
    {
        return taskContent;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Long getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(Long submitted) {
        Submitted = submitted;
    }

    public Long getUnpaid() {
        return Unpaid;
    }

    public void setUnpaid(Long unpaid) {
        Unpaid = unpaid;
    }

    public List<Student> getUnpaidList() {
        return UnpaidList;
    }

    public void setUnpaidList(List<Student> unpaidList) {
        UnpaidList = unpaidList;
    }

    public List<Answer> getAnswerList()
    {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList)
    {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("teaId", getTeaId())
            .append("taskDatetime", getTaskDatetime())
            .append("taskTitle", getTaskTitle())
            .append("taskContent", getTaskContent())
            .append("answerList", getAnswerList())
            .append("taskNum", getTaskNum())
            .append("submitted", getSubmitted())
            .append("unpaid", getUnpaid())
            .toString();
    }
}
