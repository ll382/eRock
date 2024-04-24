package com.ruoyi.match.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * C 球队内人员对象 c_personnel_sheet
 *
 * @author heye
 * @date 2024-03-19
 */
public class CPersonnelSheet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员ID */
    private Long psId;

    /** 职位ID */
    @Excel(name = "职位ID")
    private Long jobId;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long balId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long stuId;

    /** 分数 */
    @Excel(name = "分数")
    private BigDecimal psNum;

    @Excel(name = "学生名称")
    private String stuName;


    /** C 佐证信息 */
    private List<CProof> cProofList;

    public void setPsId(Long psId)
    {
        this.psId = psId;
    }

    public Long getPsId()
    {
        return psId;
    }
    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public Long getJobId()
    {
        return jobId;
    }
    public void setBalId(Long balId)
    {
        this.balId = balId;
    }

    public Long getBalId()
    {
        return balId;
    }
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
        super.setStuId(stuId);
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setPsNum(BigDecimal psNum)
    {
        this.psNum = psNum;
    }

    public BigDecimal getPsNum()
    {
        return psNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public List<CProof> getcProofList() {
        return cProofList;
    }

    public void setcProofList(List<CProof> cProofList) {
        this.cProofList = cProofList;
    }

    public List<CProof> getCProofList()
    {
        return cProofList;
    }

    public void setCProofList(List<CProof> cProofList)
    {
        this.cProofList = cProofList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("psId", getPsId())
            .append("jobId", getJobId())
            .append("balId", getBalId())
            .append("stuId", getStuId())
            .append("psNum", getPsNum())
            .append("stuName", getStuName())
            .append("cProofList", getCProofList())
            .toString();
    }
}
