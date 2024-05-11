package com.ruoyi.knowledgeQuiz.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 知识测试 问对象 a1_question
 * 
 * @author ljy
 * @date 2024-03-18
 */
public class Question extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目编号 */
    @Excel(name = "题目编号")
    private Long qqId;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String qqContent;

    /** A1 知识测试 答信息 */
    private List<Result> resultList;

    public void setQqId(Long qqId) 
    {
        this.qqId = qqId;
    }

    public Long getQqId() 
    {
        return qqId;
    }
    public void setQqContent(String qqContent) 
    {
        this.qqContent = qqContent;
    }

    public String getQqContent() 
    {
        return qqContent;
    }

    public List<Result> getResultList()
    {
        return resultList;
    }

    public void setResultList(List<Result> resultList)
    {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qqId", getQqId())
            .append("qqContent", getQqContent())
            .append("resultList", getResultList())
            .toString();
    }
}
