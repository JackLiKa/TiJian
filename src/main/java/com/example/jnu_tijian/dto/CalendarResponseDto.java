package com.example.jnu_tijian.dto;

/**
 * 后端响应前端万年历的请求，返回的响应数据对象的封装
 */
public class CalendarResponseDto {

    private String ymd;  //年月日
    private Integer total;  //hpId对应的医院某天可以预约的总号数
    private Integer existing;//hpId对应的医院某天已经预约的号数
    private Integer remainder;//余下的号数

    public String getYmd() {
        return ymd;
    }

    public CalendarResponseDto(){

    }

    @Override
    public String toString() {
        return "CalendarResponseDto{" +
                "ymd='" + ymd + '\'' +
                ", total=" + total +
                ", existing=" + existing +
                ", remainder=" + remainder +
                '}';
    }

    public CalendarResponseDto(String ymd){
        this.ymd=ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getExisting() {
        return existing;
    }

    public void setExisting(Integer existing) {
        this.existing = existing;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }
}
