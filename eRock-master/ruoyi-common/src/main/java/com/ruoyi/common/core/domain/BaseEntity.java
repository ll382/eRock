package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.Group;
import com.ruoyi.common.core.domain.entity.SelectUserVo;

/**
 * Entity基类
 *
 * @author ruoyi
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    @JsonIgnore
    private String searchValue;

    /** 创建者 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;

    /** 更新者 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updateTime;

    /** 备注 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remark;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    /** 用户ID */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Excel(name = "学生ID")
    private Long stuId;
    /** 用户ID */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Excel(name = "教师ID")
    private Long teaId;

    /** 教师类 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Excel(name = "教师类")
    private SelectUserVo teacher;

    /** 学生类 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Excel(name = "学生类")
    private SelectUserVo student;

    /** 小组ID */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Excel(name = "小组ID")
    private Long ggId;

    /** 小组信息 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Excel(name = "小组信息")
    private Group group;

    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Long getTeaId() {
        return teaId;
    }

    public void setTeaId(Long teaId) {
        this.teaId = teaId;
    }

    public Long getGgId() {
        return ggId;
    }

    public void setGgId(Long ggId) {
        this.ggId = ggId;
    }

    public SelectUserVo getTeacher() {
        return teacher;
    }

    public void setTeacher(SelectUserVo teacher) {
        this.teacher = teacher;
    }

    public SelectUserVo getStudent() {
        return student;
    }

    public void setStudent(SelectUserVo student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}