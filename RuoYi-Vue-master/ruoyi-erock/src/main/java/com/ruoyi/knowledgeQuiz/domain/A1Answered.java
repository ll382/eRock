package com.ruoyi.knowledgeQuiz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 知识测试 答题对象 a1_answered
 * 
 * @author heye
 * @date 2024-04-18
 */
public class A1Answered extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 答题ID */
    private Long ansedId;

    /** 成绩ID */
    @Excel(name = "成绩ID")
    private Long ansId;

    /** 题目编号 */
    @Excel(name = "题目编号")
    private Long qqId;

    /** 学生答案 */
    @Excel(name = "学生答案")
    private String ansedCon;

    public void setAnsedId(Long ansedId) 
    {
        this.ansedId = ansedId;
    }

    public Long getAnsedId() 
    {
        return ansedId;
    }
    public void setAnsId(Long ansId) 
    {
        this.ansId = ansId;
    }

    public Long getAnsId() 
    {
        return ansId;
    }
    public void setQqId(Long qqId) 
    {
        this.qqId = qqId;
    }

    public Long getQqId() 
    {
        return qqId;
    }
    public void setAnsedCon(String ansedCon) 
    {
        this.ansedCon = ansedCon;
    }

    public String getAnsedCon() 
    {
        return ansedCon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ansedId", getAnsedId())
            .append("ansId", getAnsId())
            .append("qqId", getQqId())
            .append("ansedCon", getAnsedCon())
            .toString();
    }
}
