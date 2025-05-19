package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ActualizacionRegisterEntity;


public interface ActualizacionRegisterRepository extends JpaRepository<ActualizacionRegisterEntity, Integer>{
	
	@Query("select l from ActualizacionRegisterEntity l where l.id = :id") 	
	public ActualizacionRegisterEntity buscarporidmateriales(@Param("id") Integer id);   
	
	@Query("select l from ActualizacionRegisterEntity l where l.id = :idmateriales")
	public List<ActualizacionRegisterEntity> buscarpornombre(@Param("idmateriales") Integer idmateriales); 


}
