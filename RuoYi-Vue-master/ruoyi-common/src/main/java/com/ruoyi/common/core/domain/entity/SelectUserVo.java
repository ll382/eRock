package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/10
 */
public class SelectUserVo {
//    角色判断
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long Id;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String Name;

    /** 学生图片 */
    @Excel(name = "学生图片")
    private String Img;

    /** 提交数量 */
    @Excel(name = "提交数量")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long doneIndex;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public Long getDoneIndex() {
        return doneIndex;
    }

    public void setDoneIndex(Long doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public String toString() {
        return "SelectUserVo{" +
                "role='" + role + '\'' +
                ", Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Img='" + Img + '\'' +
                ", doneIndex='" + doneIndex + '\'' +
                '}';
    }
}
