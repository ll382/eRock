package com.ruoyi.teachingExchange.domain;

import java.util.List;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/1
 */
public class TeachingUnit {
    private Integer unitId;
    private String unitTitle;
    private Double unitNumber;
    private List<TeachingTable> teachingTable;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public Double getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Double unitNumber) {
        this.unitNumber = unitNumber;
    }

    public List<TeachingTable> getTeachingTable() {
        return teachingTable;
    }

    public void setTeachingTable(List<TeachingTable> teachingTable) {
        this.teachingTable = teachingTable;
    }

    @Override
    public String toString() {
        return "TeachingUnit{" +
                "unitId=" + unitId +
                ", unitTitle='" + unitTitle + '\'' +
                ", unitNumber=" + unitNumber +
                ", teachingTable=" + teachingTable +
                '}';
    }
}
