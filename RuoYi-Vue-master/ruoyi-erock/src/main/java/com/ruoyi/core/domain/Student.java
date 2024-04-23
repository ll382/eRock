package com.ruoyi.core.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生对象 student
 *
 * @author heye
 * @date 2024-03-17
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private Long stuId;

    /** 小组ID */
    @Excel(name = "小组ID")
    private Long ggId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long classId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 学生密码 */
    @Excel(name = "学生密码")
    private String stuPassword;

    /** 学生头像 */
    @Excel(name = "学生头像")
    private String stuImg;

    /** 多个学生对多个老师信息 */
    private List<StuToTea> stuToTeaList;

    public Student(Long ggId) {
        this.ggId = ggId;
    }

    public Student() {
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
    public void setGgId(Long ggId)
    {
        this.ggId = ggId;
        super.setGgId(ggId);
    }

    public Long getGgId()
    {
        return ggId;
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
    public void setStuPassword(String stuPassword)
    {
        this.stuPassword = stuPassword;
    }

    public String getStuPassword()
    {
        return stuPassword;
    }

    public List<StuToTea> getStuToTeaList()
    {
        return stuToTeaList;
    }

    public void setStuToTeaList(List<StuToTea> stuToTeaList)
    {
        this.stuToTeaList = stuToTeaList;
    }

    public String getStuImg() {
        return stuImg;
    }

    public void setStuImg(String stuImg) {
        this.stuImg = stuImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("ggId", getGgId())
            .append("classId", getClassId())
            .append("stuName", getStuName())
            .append("stuPassword", getStuPassword())
            .append("stuToTeaList", getStuToTeaList())
            .append("stuImg", getStuImg())
            .toString();
    }
}
