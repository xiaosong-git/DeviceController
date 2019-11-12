package com.xs.dc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public class DcAuthority {
    private Long id;

    private String authorityName;

    private String authorityGrade;

    private Long superId;
    
    private String resourceUrl;
    
    @Transient
    private List<DcAuthority> children = new ArrayList<DcAuthority>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getAuthorityGrade() {
        return authorityGrade;
    }

    public void setAuthorityGrade(String authorityGrade) {
        this.authorityGrade = authorityGrade == null ? null : authorityGrade.trim();
    }

    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public List<DcAuthority> getChildren() {
		return children;
	}

	public void setChildren(List<DcAuthority> children) {
		this.children = children;
	}
    
    
}