
package com.example.sample.controller;

import com.example.sample.DTO.CouponDTO;
import com.example.sample.DTO.FestivalDTO;
import com.example.sample.DTO.ProductDTO;

import com.example.sample.Model.Category;
import com.example.sample.Model.Coupon;
import com.example.sample.Model.Festival;
import com.example.sample.Model.Product;
import com.example.sample.Repository.FestivalRepository;
import com.example.sample.Service.CategoryService;
import com.example.sample.Service.CouponService;
import com.example.sample.Service.FestivalService;
import com.example.sample.Service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller


//////===================Category Section==============================////
public class AdminController {
    
    
    
    public static  String uploadDir=System.getProperty("user.dir")+ "/src/main/resources/static/productImages";
    
    @Autowired
    CategoryService categoryService; 
    
    @Autowired
    ProductService productService;
    
    
    
    @Autowired
    FestivalService festivalService;
    
    
    @Autowired
    CouponService couponService;
    
    

    @GetMapping("/admin")
    public String adminHome(){
return "adminHome";
    }

 @GetMapping("/categories")
    public String getcategories(Model model){
        
         model.addAttribute("categories", categoryService.getAllCategory());
   return "categories";
    }
    
    @GetMapping("/categories/add")
    public String getcatadd(Model model){
        
        //object pass
        model.addAttribute("category", new Category());
return "categoriesAdd";
    }
    
    
  //==============adding category=========================
    
    @PostMapping("/categories/add")
    public String postcatadd(@ModelAttribute("category") Category category){
            categoryService.addCategory(category);
        
return "redirect:/categories";
    }
   
    
  //==============delete category=========================
    
    ///deleting on basis category_id id is fetch to delete
    //route
    @GetMapping("/categories/delete/{id}")
    
    
    public String deletecat(@PathVariable int id){
    
        categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
    
    
    
  //==============update category=========================
    
    //update
    //route
    
    
    @GetMapping("/categories/update/{id}")
    
    ///check
     public String updateecat(@PathVariable int id, Model model){
    Optional<Category> category=categoryService.getCategoryById(id);
    
    
    if(category.isPresent()){
    model.addAttribute("category", category.get());
    return "categoriesAdd";
    }else
        return "404";
        
    }
    
    //////===================Product Section==============================////
     
     
     
     
     
     
     //route
//     
     @GetMapping("/products")
    
    
    public String product(Model model){
    
       model.addAttribute("products", productService.getAllProduct());
        return "products";
    }
    
    
    
     //==============adding product=========================
     
     @GetMapping("/products/add")
    public String productadd(Model model){
        
        //object pass
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
     
     //
    }
     
     
     //==============adding file photo,other attributes=========================
     
     
     
     @PostMapping("/products/add")
     //frontend to html-
             //productImage in param request 
     //productImage client to request in request param object
     //DTO is handy
     //multipart file
     //
     public String productaddpost(@ModelAttribute("productDTO")ProductDTO productDTO,
             @RequestParam("productImage")MultipartFile file,
             @RequestParam("imgName") String imgName) throws IOException{
         
         //productDTO object into product object
         //map conceptis there too
         
         Product product=new Product();
         
         //Paths
         //using path
         //using package nio
         //import java.nio.file.Files;
         //import java.nio.file.Path;
         //import java.nio.file.Paths;
         //path reference
         //file path and filename saved is been hold
         //object working behind scenes
         
         //write,filename,filepath,actual data -bytes that is write 
         
         product.setId(productDTO.getId());
         product.setName(productDTO.getName());
         //product model whole category object provides we have just id we need to fetch catgory id
         
         
         //categoryService-id basis origin category object fetch
         product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
         
         product.setPrice(productDTO.getPrice());
         //product.setWeight(productDTO.getWeight());
         product.setDescription(productDTO.getDescription());
         
         //image 2 part 1.database 2.static1
         
         //self generated uuid-128bit no token,images names. we cant use original name of  img
         String imageUUID; 
        
         //false value
         if(!file.isEmpty()){
         
         imageUUID= file.getOriginalFilename();
         
         //path class
         Path fileNameAndPath= Paths.get(uploadDir, imageUUID);
         Files.write(fileNameAndPath, file.getBytes());
         
         }else{
         
         
         imageUUID=imgName;
         }
         
         
         product.setImageName(imageUUID);
         productService.addProduct(product);
     
     return "redirect:/products";
     }
     
     
     
     
//=====================================delete product=========================
     
     ///admin/product/delete/{id}
     
     @GetMapping("/product/delete/{id}")
     
     
     public String deleteproduct(@PathVariable long id){
     
         productService.deleteProductById(id);
         return "redirect:/products";
     }
     
     
     
 //=====================update product================================================
     
     
     
     
     @GetMapping("/product/update/{id}")
     
     
      public String updateproductget(@PathVariable long id, Model model){
    Product product=productService.getProductById(id).get();
     
     ProductDTO productDTO=new ProductDTO();
    
    
    productDTO.setId(product.getId());
    
    productDTO.setName(product.getName());
    
    productDTO.setCategoryId(product.getCategory().getId());
    
    productDTO.setPrice(product.getPrice());
    //productDTO.setWeight(product.getWeight());
    productDTO.setDescription(product.getDescription());
    productDTO.setImageName(product.getImageName());
    
    model.addAttribute("categories", categoryService.getAllCategory());
    model.addAttribute("productDTO",  productDTO);
     return "productsAdd";
     
     //hikari pool
     
     }
     
     
   //================================Festival Section========================================================
     
     
     



     @GetMapping("/admin/festivals")
         public String getfestival(Model model){
             
              model.addAttribute("festivals", festivalService.getAllFestival());
        return "festival";
         }
      //==============adding festival get and post========================================================
      
      @GetMapping("/admin/festivals/add")
      public String getfestivaladd(Model model){
          
          //object pass
         
          model.addAttribute("festivalDTO", new FestivalDTO());
          model.addAttribute("categories", categoryService.getAllCategory());
          
 return "festivalsAdd";
      }
      
      
   
      
      @PostMapping("/admin/festivals/add")
//      public String postfestivaladd(@ModelAttribute("festival") Festival festival){
//          
//          
//              festivalService.addFestival(festival);
//          
 // return "redirect:/admin/festivals";
      public String festivaladdpost(@ModelAttribute("festivalDTO")FestivalDTO festivalDTO)
              
              throws IOException{
          
          
          
          Festival festival=new Festival();
          
          
          
          festival.setFid(festivalDTO.getFid());
          festival.setFname(festivalDTO.getFname());
         
          
          
          
          festival.setCategory(categoryService.getCategoryById(festivalDTO.getCategoryId()).get());
          
          festival.setFstartdate(festivalDTO.getFstartdate());
          
          festival.setFenddate(festivalDTO.getFenddate());
          festivalService.addFestival(festival);
          return "redirect:/admin/festivals";
      }



     
      
      
      //==============delete festivals =======================================================================
        
        ///deleting on basis category_id id is fetch to delete
        //route
        @GetMapping("/admin/festivals/delete/{fid}")
        
        
        public String deletefestival(@PathVariable int fid){
        
            festivalService.deleteFestivalById(fid);
            return "redirect:/admin/festivals";
        }
        
     
        
        



       //==============update festivals=====================================================================
          
          //update
          //route
          
          
//          @GetMapping("/admin/festivals/update/{fid}")
//          
//          ///check
//           public String updatefestivals(@PathVariable int fid, Model model){
//          Optional<Festival> festival=festivalService.getFestivalById(fid);
//          
//          
//          if(festival.isPresent()){
//          model.addAttribute("festival", festival.get());
//          return "festivalsAdd";
//          }else
//              return "404";
//              
//          }
        
        @GetMapping("/admin/festivals/update/{fid}")
        public String updateFestivalGet(@PathVariable int fid, Model model)
        {
            Festival festival=festivalService.getFestivalById(fid).get();
            FestivalDTO festivalDTO= new FestivalDTO();
            festivalDTO.setFid(festival.getFid());
            festivalDTO.setFname(festival.getFname());
            festivalDTO.setCategoryId(festival.getCategory().getId());
            festivalDTO.setFstartdate(festival.getFstartdate());
            festivalDTO.setFenddate(festival.getFenddate());
          
            
            model.addAttribute("categories",categoryService.getAllCategory());
            model.addAttribute("festivalDTO",festivalDTO);
            return "festivalsAdd";
        }
     
     
     
        
        
        //================================Coupon Section========================================================
          
          
          

          @GetMapping("/coupons")
             public String getcoupon(Model model){
                 
                  model.addAttribute("coupons", couponService.getAllCoupon());
            return "coupon";
             }
          //==============adding festival get and post========================================================
          
          @GetMapping("/coupons/add")
          public String getcouponadd(Model model){
              
              //object pass
             
              model.addAttribute("couponDTO", new CouponDTO());
              model.addAttribute("festivals", festivalService.getAllFestival());
              
      return "couponsAdd";
          }
          
          
       
          
          @PostMapping("/coupons/add")
          public String couponaddpost(@ModelAttribute("couponDTO")CouponDTO couponDTO)
                  
                  throws IOException{
              
              
              
              Coupon coupon=new  Coupon();
              
              
              
              coupon.setCpid(couponDTO.getCpid());
              coupon.setCpname(couponDTO.getCpname());
             
              
              
              
              coupon.setFestival(festivalService.getFestivalById(couponDTO.getFid()).get());
              
              coupon.setCpdiscount(couponDTO.getCpdiscount());
              
              couponService.addCoupon(coupon);
              return "redirect:/coupons";
          }

          
          
          
          //==============delete festivals =======================================================================
            
            ///deleting on basis category_id id is fetch to delete
            //route
            @GetMapping("/coupons/delete/{cpid}")
            
            
            public String deletecoupon(@PathVariable int cpid){
            
           	 couponService.removeCouponById(cpid);
                return "redirect:/coupons";
            }
            
         
            
            

            //==============update festivals=====================================================================
              
            
            @GetMapping("/coupons/update/{cpid}")
            public String updateCouponGet(@PathVariable int cpid, Model model)
            {
           	 Coupon coupon=couponService.getCouponById(cpid).get();
           	 CouponDTO couponDTO= new CouponDTO();
           	 couponDTO.setCpid(coupon.getCpid());
           	 couponDTO.setCpname(coupon.getCpname());
           	 
           	 couponDTO.setFid(coupon.getFestival().getFid());
           	 couponDTO.setCpdiscount(coupon.getCpdiscount());
              
                
                model.addAttribute("festivals",festivalService.getAllFestival());
                model.addAttribute("couponDTO",couponDTO);
                return "couponsAdd";
            }
        
        
        
     
     
     
     
}

