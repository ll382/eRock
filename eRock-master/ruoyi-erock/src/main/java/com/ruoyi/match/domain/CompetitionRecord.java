package com.ruoyi.match.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * C 比赛记录对象 c_competition_record
 *
 * @author houq
 * @date 2024-04-08
 */
public class CompetitionRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long ccRId;

    /** 枚举ID */
    @Excel(name = "枚举ID")
    private Long enumId;

    /** 场次时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "场次时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ccRTime;

    /** 比赛规格 */
    @Excel(name = "比赛规格")
    private String ccRSpeci;

    /** 比赛名称 */
    @Excel(name = "比赛名称")
    private String ccRName;

    /** 审核状态
     0：未提交
     1：未审核
     2：已审核 */
    @Excel(name = "审核状态 0：未提交 1：未审核 2：已审核")
            private Long ccRAudit;

            /** 课堂ID */
            @Excel(name = "课堂ID")
            private Long crId;

            /** 球队参赛信息 */
            private List<CBallteam> cBallteamList;

            public void setCcRId(Long ccRId)
    {
        this.ccRId = ccRId;
    }

    public Long getCcRId()
    {
        return ccRId;
    }
    public void setEnumId(Long enumId)
    {
        this.enumId = enumId;
    }

    public Long getEnumId()
    {
        return enumId;
    }
    public void setCcRTime(Date ccRTime)
    {
        this.ccRTime = ccRTime;
    }

    public Date getCcRTime()
    {
        return ccRTime;
    }
    public void setCcRSpeci(String ccRSpeci)
    {
        this.ccRSpeci = ccRSpeci;
    }

    public String getCcRSpeci()
    {
        return ccRSpeci;
    }
    public void setCcRName(String ccRName)
    {
        this.ccRName = ccRName;
    }

    public String getCcRName()
    {
        return ccRName;
    }
    public void setCcRAudit(Long ccRAudit)
    {
        this.ccRAudit = ccRAudit;
    }

    public Long getCcRAudit()
    {
        return ccRAudit;
    }
    public void setCrId(Long crId)
    {
        this.crId = crId; 
    }

    public Long getCrId()
    {
        return crId;
    }

    public List<CBallteam> getCBallteamList()
    {
        return cBallteamList;
    }

    public void setCBallteamList(List<CBallteam> cBallteamList)
    {
        this.cBallteamList = cBallteamList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ccRId", getCcRId())
                .append("enumId", getEnumId())
                .append("ccRTime", getCcRTime())
                .append("ccRSpeci", getCcRSpeci())
                .append("ccRName", getCcRName())
                .append("ccRAudit", getCcRAudit())
                .append("crId", getCrId())
                .append("cBallteamList", getCBallteamList())
                .toString();
    }
}
