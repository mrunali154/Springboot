package com.example.sample.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Coupon {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
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
	public Festival getFestival() {
		return festival;
	}
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	private String cpname;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fid" ,referencedColumnName ="fid")
	private Festival festival;
	
	private double cpdiscount;
	
}
