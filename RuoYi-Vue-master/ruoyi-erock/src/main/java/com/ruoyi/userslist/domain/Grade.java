package com.ruoyi.userslist.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级对象 grade
 * 
 * @author eRock
 * @date 2024-03-13
 */
public class Grade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long classId;

    /** 专业班级名 */
    @Excel(name = "专业班级名")
    private String className;

    /** 学生信息 */
    private List<Student> studentList;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }

    public List<Student> getStudentList()
    {
        return studentList;
    }

    public void setStudentList(List<Student> studentList)
    {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("studentList", getStudentList())
            .toString();
    }
}
