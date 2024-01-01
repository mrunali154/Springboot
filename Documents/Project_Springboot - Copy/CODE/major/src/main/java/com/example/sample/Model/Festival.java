package com.example.sample.Model;

import java.sql.Date;


import javax.persistence.Column;
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
public class Festival {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	 //@Column(name= "fid")

  
    
	private int fid;
	
	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public Date getFstartdate() {
		return fstartdate;
	}


	public void setFstartdate(Date fstartdate) {
		this.fstartdate = fstartdate;
	}


	public Date getFenddate() {
		return fenddate;
	}


	public void setFenddate(Date fenddate) {
		this.fenddate = fenddate;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	
    //object
    //DTO-Data transfer object
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="category_id" ,referencedColumnName ="category_id")
	  private Category category;
	  private String fname;
		private Date fstartdate;
		private Date fenddate;

}
