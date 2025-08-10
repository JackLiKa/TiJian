package com.example.jnu_tijian.entity;

public class Doctor {
    private String docId;

    private String docCode;

    private String realName;

    private String password;

    private Integer sex;

    private Integer deptno;

    @Override
    public String toString() {
        return "Doctor{" +
                "docId=" + docId +
                ", docCode='" + docCode + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", deptno=" + deptno +
                '}';
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode == null ? null : docCode.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
