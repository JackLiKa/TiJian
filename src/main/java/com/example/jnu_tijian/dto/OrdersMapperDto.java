package com.example.jnu_tijian.dto;

public class OrdersMapperDto {

    private Integer hpId;
    private String date;   //ymd

    public OrdersMapperDto(Integer hpId, String date) {
        this.hpId = hpId;
        this.date = date;
    }

    public OrdersMapperDto() {

    }

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
