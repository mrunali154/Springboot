package com.example.sample.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.sample.Model.Festival;

public interface FestivalRepository  extends JpaRepository<Festival, Integer>{
	List<Festival>findAllByCategory_Id(int id);
}
