package com.example.sample.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.Model.Festival;
import com.example.sample.Repository.FestivalRepository;


@Service
public class FestivalService {
	 @Autowired
	    
	 FestivalRepository  festivalRepository;
	    
	    //
	    
	    public List<Festival>getAllFestival(){
	    
	    return  festivalRepository.findAll();
	    }
	    
	    
	    //Festival object
	    public void addFestival(Festival festival){
	        
	    	festivalRepository.save(festival);
	    
	    
	    }
	    //METHoD return FestivalByIdpublic void 
	     public void deleteFestivalById(int id){festivalRepository.deleteById(id);}
	    
	     
	     //optional whetrher id may present or not
	     public Optional<Festival> getFestivalById(int id){
	         return festivalRepository.findById(id);
	     }
	     
	     public List<Festival>getAllFestivalByCategoryId(int id){
	         return festivalRepository.findAllByCategory_Id(id);
	         }
}
