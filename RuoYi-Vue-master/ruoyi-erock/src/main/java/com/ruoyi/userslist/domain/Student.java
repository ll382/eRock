package com.ruoyi.userslist.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生对象 student
 *
 * @author ruoyi
 * @date 2024-03-13
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    private String stuId;

    /** 学生互评ID */
    @Excel(name = "学生互评ID")
    private Long seId;

    /** 小组ID */
    @Excel(name = "小组ID")
    private Long gId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long classId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    public void setStuId(String stuId)
    {
        this.stuId = stuId;
    }

    public String getStuId()
    {
        return stuId;
    }
    public void setSeId(Long seId)
    {
        this.seId = seId;
    }

    public Long getSeId()
    {
        return seId;
    }
    public void setGId(Long gId)
    {
        this.gId = gId;
    }

    public Long getGId()
    {
        return gId;
    }
    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }
    public void setStuName(String stuName)
    {
        this.stuName = stuName;
    }

    public String getStuName()
    {
        return stuName;
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
            .append("stuId", getStuId())
            .append("seId", getSeId())
            .append("gId", getGId())
            .append("classId", getClassId())
            .append("stuName", getStuName())
            .append("password", getPassword())
            .toString();
    }
}
