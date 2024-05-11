package com.ruoyi.knowledgeQuiz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * A1 知识测试 答对象 a1_result
 * 
 * @author ljy
 * @date 2024-03-18
 */
public class Result extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 答案ID */
    @Excel(name = "答案ID")
    private Long ooId;

    /** 题目编号 */
    @Excel(name = "题目编号")
    private Long qqId;

    /** 答案内容 */
    @Excel(name = "答案内容")
    private String ooContent;

    /** 是否为答案 */
    @Excel(name = "是否为答案")
    private Long result;

    public void setOoId(Long ooId) 
    {
        this.ooId = ooId;
    }

    public Long getOoId() 
    {
        return ooId;
    }
    public void setQqId(Long qqId) 
    {
        this.qqId = qqId;
    }

    public Long getQqId() 
    {
        return qqId;
    }
    public void setOoContent(String ooContent) 
    {
        this.ooContent = ooContent;
    }

    public String getOoContent() 
    {
        return ooContent;
    }
    public void setResult(Long result) 
    {
        this.result = result;
    }

    public Long getResult() 
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ooId", getOoId())
            .append("qqId", getQqId())
            .append("ooContent", getOoContent())
            .append("result", getResult())
            .toString();
    }
}
