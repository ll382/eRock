package com.ruoyi.score.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * D 模块成绩对象 d_model_score
 *
 * @author heye
 * @date 2024-04-25
 */
public class DModelScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模块成绩主键 */
    private Long modscId;

    /** 总分ID */
    @Excel(name = "总分ID")
    private Long tsId;

    /** 运球进步分 */
    @Excel(name = "运球进步分")
    private BigDecimal modscDribble;

    /** 投篮进步分 */
    @Excel(name = "投篮进步分")
    private BigDecimal modscShoot;

    /** 附加分 */
    @Excel(name = "附加分")
    private BigDecimal modscAdditional;

    public DModelScore() {
    }


    public DModelScore(Long tsId) {
        this.tsId = tsId;
    }

    public void setModscId(Long modscId)
    {
        this.modscId = modscId;
    }

    public Long getModscId()
    {
        return modscId;
    }
    public void setTsId(Long tsId)
    {
        this.tsId = tsId;
    }

    public Long getTsId()
    {
        return tsId;
    }
    public void setModscDribble(BigDecimal modscDribble)
    {
        this.modscDribble = modscDribble;
    }

    public BigDecimal getModscDribble()
    {
        return modscDribble;
    }
    public void setModscShoot(BigDecimal modscShoot)
    {
        this.modscShoot = modscShoot;
    }

    public BigDecimal getModscShoot()
    {
        return modscShoot;
    }
    public void setModscAdditional(BigDecimal modscAdditional)
    {
        this.modscAdditional = modscAdditional;
    }

    public BigDecimal getModscAdditional()
    {
        return modscAdditional;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modscId", getModscId())
            .append("tsId", getTsId())
            .append("modscDribble", getModscDribble())
            .append("modscShoot", getModscShoot())
            .append("modscAdditional", getModscAdditional())
            .toString();
    }
}
