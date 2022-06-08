package com.huing.pojo;

/**
 * @author huing
 * @create 2022-06-07 16:01
 */
public class Admin {
    private Integer aId;

    private String aName;

    private String aPass;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName == null ? null : aName.trim();
    }

    public String getaPass() {
        return aPass;
    }

    public void setaPass(String aPass) {
        this.aPass = aPass == null ? null : aPass.trim();
    }
}