package com.example.sample.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sample.DTO.CouponDTO;
import com.example.sample.Model.Coupon;
import com.example.sample.Model.Festival;
import com.example.sample.Model.Product;
import com.example.sample.Service.CouponService;
import com.example.sample.Service.ProductService;
import com.example.sample.global.GlobalData;



@Controller
public class CartController {

	@Autowired
	ProductService productService;

	@Autowired
	CouponService couponService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}

	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}

	
	@PostMapping("/applycoupon")

	public String applycoupon(@RequestParam("couponcode") String couponcode, Model model)

	{
		Coupon cp=couponService.getCouponByName(couponcode);
		   long millis = System.currentTimeMillis();
		   double sum12=0;
		   java.sql.Date date = new java.sql.Date(millis);  
		   Date d1=cp.getFestival().getFstartdate();
		   Date d2=cp.getFestival().getFenddate();
		   
		   if(date.compareTo(d1)>=0) {
			   if(date.compareTo(d2)<=0) {
				   
					List<Coupon>  list = couponService.getAllCoupon();
					model.addAttribute("discountAmt",0);

					for(Coupon c :list) {
						if(c.getCpname().equals(couponcode))
					{
					sum12=GlobalData.cart.stream().mapToDouble(Product::getPrice).sum()-Integer.parseInt(couponcode);
					model.addAttribute("discountAmt",sum12);
						System.out.println(sum12);
									}
							}

					model.addAttribute("cartCount",GlobalData.cart.size());
					model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

					model.addAttribute("cart",GlobalData.cart);
					
					return "cart";
				   
			   }
			   else {
				   model.addAttribute("discountAmt",sum12);
			   }
		   }
		   else
			   model.addAttribute("discountAmt",sum12);
			   return "redirect:/shop";
		
	}
	
	}