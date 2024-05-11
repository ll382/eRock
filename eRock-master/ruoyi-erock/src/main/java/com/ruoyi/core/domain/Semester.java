package com.ruoyi.core.domain;

import java.util.Date;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/11
 */
public class Semester {
    private Long semesterId;
    private Date startDate;
    private Date finishDate;

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semesterId=" + semesterId +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
