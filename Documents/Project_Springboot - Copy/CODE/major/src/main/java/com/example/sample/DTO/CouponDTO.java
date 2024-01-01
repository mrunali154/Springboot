package com.example.sample.DTO;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
public class CouponDTO {
	private int cpid;

	public int getCpid() {
		return cpid;
	}
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public double getCpdiscount() {
		return cpdiscount;
	}
	public void setCpdiscount(double cpdiscount) {
		this.cpdiscount = cpdiscount;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	
	private String cpname;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fid" ,referencedColumnName ="fid")
	private int fid;
	

	
	private double cpdiscount;

}
