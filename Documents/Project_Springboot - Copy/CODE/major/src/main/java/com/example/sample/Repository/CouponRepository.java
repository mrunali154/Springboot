package com.example.sample.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.Model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	List<Coupon>findAllByFestival_Fid(int fid);

	Coupon findByCpname(String cpname);
//	Optional<Coupons> findByName(String name);
}
