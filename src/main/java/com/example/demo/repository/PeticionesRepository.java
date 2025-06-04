package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.PeticionesDTO;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.PeticionesEntity;


public interface PeticionesRepository extends JpaRepository <PeticionesEntity, Integer> {
	
	List<PeticionesEntity> findByNomsolContainingIgnoreCase(String nomsol);
	
	@Query("select l from PeticionesEntity l where l.id = :id") 	
	public PeticionesEntity buscarporidpeticiones(@Param("id") Integer id);  

	@Query("select l from PeticionesEntity l where l.id = :nomsol")
	public List<PeticionesEntity> buscarporsolicitante(@Param("nomsol") String nomsol); 

	@Query("SELECT NEW com.example.demo.dto.PeticionesDTO ("
			+ "c.id, "
			+ "c.aresol, "
			+ "c.nomsol, "
			+ "c.cansol, "
			+ "c.fepe, "
			+ "c.espe, "
			+ "c.feel, "
			+ "c.cantidis, "
			+ "m.id, "
			+ "m.cla, "
			+ "m.nom, "
			+ "m.us, "
			+ "m.pre "
			+ ") FROM PeticionesEntity c join c.totalcan m")
	public List<PeticionesDTO> listarPeticiones();



	
}
