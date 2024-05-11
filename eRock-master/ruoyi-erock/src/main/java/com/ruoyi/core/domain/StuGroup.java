package com.ruoyi.core.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 小组对象 stu_group
 *
 * @author heye
 * @date 2024-03-15
 */
public class StuGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小组ID */
    private Long ggId;

    /** 小组名 */
    @Excel(name = "小组名")
    private String ggName;

    /** 小组组长 */
    @Excel(name = "小组组长")
    private Long stuGroupLeader;

    /** 学生信息 */
    private List<Student> studentList;

    public void setGgId(Long ggId)
    {
        this.ggId = ggId;
        super.setGgId(ggId);
    }

    public Long getGgId()
    {
        return ggId;
    }
    public void setGgName(String ggName)
    {
        this.ggName = ggName;
    }

    public String getGgName()
    {
        return ggName;
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
            .append("ggId", getGgId())
            .append("ggName", getGgName())
            .append("stuGroupLeader", getStuGroupLeader())
            .append("studentList", getStudentList())
            .toString();
    }
}
