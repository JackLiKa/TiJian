package com.example.jnu_tijian.entity;

import java.util.List;

public class Setmeal {
    private Integer smId;

    private String name;

    private Integer type;

    private Integer price;

    private List<Checkitem> checkitemList;

    public List<Checkitem> getCheckitemList() {
        return checkitemList;
    }

    @Override
    public String toString() {
        return "Setmeal{" +
                "smId=" + smId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", checkitemList=" + checkitemList +
                '}';
    }

    public void setCheckitemList(List<Checkitem> checkitemList) {
        this.checkitemList = checkitemList;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }
}
