package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/10
 */
public class SelectUserVo {
    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String Name;

    /** 学生图片 */
    @Excel(name = "学生图片")
    private String Img;

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

    @Override
    public String toString() {
        return "SelectUserVo{" +
                "Name='" + Name + '\'' +
                ", Img='" + Img + '\'' +
                '}';
    }
}
