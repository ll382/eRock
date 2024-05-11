package com.ruoyi.practice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 练习资源表对象 a_exercise_resource
 *
 * @author houq
 * @date 2024-04-09
 */
public class AExerciseResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Long erId;

    /** 评分ID */
    @Excel(name = "评分ID")
    private Long msId;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date erTime;

    /** 练习视频/图片 */
    @Excel(name = "练习视频/图片")
    private String erSource;

    public void setErId(Long erId)
    {
        this.erId = erId;
    }

    public Long getErId()
    {
        return erId;
    }
    public void setMsId(Long msId)
    {
        this.msId = msId;
    }

    public Long getMsId()
    {
        return msId;
    }
    public void setErTime(Date erTime)
    {
        this.erTime = erTime;
    }

    public Date getErTime()
    {
        return erTime;
    }

    public void setErSource(String erSource)
    {
        this.erSource = erSource;
    }

    public String getErSource()
    {
        return erSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("erId", getErId())
            .append("msId", getMsId())
            .append("erTime", getErTime())
            .append("erSource", getErSource())
            .toString();
    }
}
