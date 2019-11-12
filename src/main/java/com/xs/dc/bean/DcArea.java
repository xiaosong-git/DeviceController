package com.xs.dc.bean;

import java.util.Date;

public class DcArea {
    private Long id;

    private String orgCode;

    private String orgName;

    private String orgTel;

    private String orgAddr;

    private Date createdate;

    private String orgPrivince;

    private String orgCity;

    private String orgArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel == null ? null : orgTel.trim();
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr == null ? null : orgAddr.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getOrgPrivince() {
        return orgPrivince;
    }

    public void setOrgPrivince(String orgPrivince) {
        this.orgPrivince = orgPrivince == null ? null : orgPrivince.trim();
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity == null ? null : orgCity.trim();
    }

    public String getOrgArea() {
        return orgArea;
    }

    public void setOrgArea(String orgArea) {
        this.orgArea = orgArea == null ? null : orgArea.trim();
    }
}