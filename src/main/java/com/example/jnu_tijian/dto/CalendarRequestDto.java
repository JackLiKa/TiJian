package com.example.jnu_tijian.dto;

/**
 * 对应日历界面的  前端请求数据封装对象
 */
public class CalendarRequestDto {

    private Integer hpId;
    private Integer year;
    private Integer month;

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}