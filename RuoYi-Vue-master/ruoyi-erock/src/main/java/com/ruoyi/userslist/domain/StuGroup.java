package com.ruoyi.userslist.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小组对象 stu_group
 *
 * @author ruoyi
 * @date 2024-03-13
 */
public class StuGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小组ID */
    private Long gId;

    /** 小组名 */
    @Excel(name = "小组名")
    private String gName;

    /** 小组组长 */
    @Excel(name = "小组组长")
    private Long stuGroupLeader;

    /** 学生信息 */
    private List<Student> studentList;

    public void setGId(Long gId)
    {
        this.gId = gId;
    }

    public Long getGId()
    {
        return gId;
    }
    public void setgName(String gName)
    {
        this.gName = gName;
    }

    public String getgName()
    {
        return gName;
    }
    public void setStuGroupLeader(Long stuGroupLeader)
    {
        this.stuGroupLeader = stuGroupLeader;
    }

    public Long getStuGroupLeader()
    {
        return stuGroupLeader;
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
            .append("gId", getGId())
            .append("gName", getgName())
            .append("stuGroupLeader", getStuGroupLeader())
            .append("studentList", getStudentList())
            .toString();
    }
}
