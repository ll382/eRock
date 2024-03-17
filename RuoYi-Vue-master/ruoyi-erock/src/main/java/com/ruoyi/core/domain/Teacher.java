package com.ruoyi.core.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 老师对象 teacher
 * 
 * @author heye
 * @date 2024-03-17
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老师工号 */
    private Long teaId;

    /** 老师姓名 */
    @Excel(name = "老师姓名")
    private String teaName;

    /** 学生密码 */
    @Excel(name = "学生密码")
    private String teaPassword;

    /** 多个学生对多个老师信息 */
    private List<StuToTea> stuToTeaList;

    public void setTeaId(Long teaId) 
    {
        this.teaId = teaId;
    }

    public Long getTeaId() 
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
    public void setTeaPassword(String teaPassword) 
    {
        this.teaPassword = teaPassword;
    }

    public String getTeaPassword() 
    {
        return teaPassword;
    }

    public List<StuToTea> getStuToTeaList()
    {
        return stuToTeaList;
    }

    public void setStuToTeaList(List<StuToTea> stuToTeaList)
    {
        this.stuToTeaList = stuToTeaList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teaId", getTeaId())
            .append("teaName", getTeaName())
            .append("teaPassword", getTeaPassword())
            .append("stuToTeaList", getStuToTeaList())
            .toString();
    }
}
