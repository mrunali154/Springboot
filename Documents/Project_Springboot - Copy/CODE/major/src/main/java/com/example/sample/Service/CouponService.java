package com.example.sample.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.Model.Coupon;
import com.example.sample.Repository.CouponRepository;

@Service
public class CouponService {
	@Autowired
    CouponRepository couponRepository;
    
    
     public List<Coupon>getAllCoupon(){
    
    return  couponRepository.findAll();
    }
     
     
     //Coupon object
    public void addCoupon(Coupon coupon){
        
    	couponRepository.save(coupon);
    
    
    }
    
    
    //METHoD return deleteById public void ddeletecouponById
     public void removeCouponById(int cpid){couponRepository.deleteById(cpid);}
     
     
     
     //optional whetrher id may present or not
     public Optional<Coupon> getCouponById(int cpid){
         return couponRepository.findById(cpid);
     }
    
     
     public Coupon getCouponByName(String cpname){
         return couponRepository.findByCpname(cpname);
     }
    
     
     //user section
     
      public List<Coupon>getAllProductsByFestivalId(int fid){
      return couponRepository.findAllByFestival_Fid(fid);
      }
      
   
      public Coupon findByCpname(String applycoupon) {
    	  return null;
    	  }
}
