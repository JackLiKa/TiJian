package com.example.jnu_tijian.entity;

public class Hospital {
    private Integer hpId;
    private String name;
    private String picture;
    private String telephone;
    private String address;
    private String businessHours;
    private String deadline;
    private String rule;
    private String state;

    @Override
    public String toString() {
        return "Hospital{" +
                "hpId='" + hpId + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", businessHours='" + businessHours + '\'' +
                ", deadline='" + deadline + '\'' +
                ", rule='" + rule + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
