package com.example.sample.DTO;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.sample.Model.Category;

import lombok.Data;


@Data
public class FestivalDTO {
	
	private int fid;
	

    //object
    //DTO-Data transfer object
   


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
	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	 //@Column(name= "festival_id")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="category_id" ,referencedColumnName ="category_id")
   
   
   private int categoryId;
	 private String fname;
		private Date fstartdate;
		
		private Date fenddate;
		

}
