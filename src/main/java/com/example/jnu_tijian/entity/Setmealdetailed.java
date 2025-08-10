package com.example.jnu_tijian.entity;

public class Setmealdetailed {
    private Integer sdId;

    private Integer smId;

    private Integer ciId;

    @Override
    public String toString() {
        return "Setmealdetailed{" +
                "sdId=" + sdId +
                ", smId=" + smId +
                ", ciId=" + ciId +
                '}';
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

}
