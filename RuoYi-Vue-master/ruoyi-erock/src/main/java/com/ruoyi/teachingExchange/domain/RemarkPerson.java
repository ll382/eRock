package com.ruoyi.teachingExchange.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * @Description
 * @Author heye
 * @Date 2024/3/21
 */
public class RemarkPerson {
    @Excel(name = "用户名")
    private String username;

    @Excel(name = "用户ID")
    private String userId;

    @Excel(name = "头像")
    private String avatarUrl;

    @Excel(name = "角色")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RemarkPerson(String username, String userId, String avatarUrl, String role) {
        this.username = username;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }
}
