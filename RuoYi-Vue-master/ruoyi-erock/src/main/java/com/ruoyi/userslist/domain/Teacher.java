package com.ruoyi.userslist.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 老师对象 teacher
 *
 * @author ruoyi
 * @date 2024-03-12
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老师工号 */
    private String teaId;

    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teaName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    public void setTeaId(String teaId)
    {
        this.teaId = teaId;
    }

    public String getTeaId()
    {
        return teaId;
    }
    public void setTeaName(String teaName)
    {
        this.teaName = teaName;
    }

    public String getTeaName()
    {
        return teaName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teaId", getTeaId())
            .append("teaName", getTeaName())
            .append("password", getPassword())
            .toString();
    }
}
