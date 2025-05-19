package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserCorrect;


@Repository
public interface UserCorrectRepository extends JpaRepository<UserCorrect, Integer>{ 
	
	@Query("select l from UserCorrect l where l.id = :id") 	
	public UserCorrect buscarporidusuario(@Param("id") Integer id);  
	
	@Query("select l from UserCorrect l where l.nom = :nombre")
	public List<UserCorrect> buscarporusuario(@Param("nombre") String nombre); 

}


