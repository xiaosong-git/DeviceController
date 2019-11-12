package com.xs.dc.bean;

import java.util.List;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年9月26日 下午5:26:58 类说明
 */
public class DcMenu {
	private Long id;

	private String authorityName;

	private String authorityGrade;

	private Long superId;
	
	private List<DcAuthority> listAuthority;

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
		this.authorityName = authorityName;
	}

	public String getAuthorityGrade() {
		return authorityGrade;
	}

	public void setAuthorityGrade(String authorityGrade) {
		this.authorityGrade = authorityGrade;
	}

	public Long getSuperId() {
		return superId;
	}

	public void setSuperId(Long superId) {
		this.superId = superId;
	}

	public List<DcAuthority> getListAuthority() {
		return listAuthority;
	}

	public void setListAuthority(List<DcAuthority> listAuthority) {
		this.listAuthority = listAuthority;
	}
	
	
}
