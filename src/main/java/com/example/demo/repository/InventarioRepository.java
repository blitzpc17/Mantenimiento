package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.InventarioDTO;
import com.example.demo.entity.InventarioEntity;

public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {

	List<InventarioEntity> findByNomContainingIgnoreCase(String nom);

	@Query("select l from InventarioEntity l where l.id = :id")
	public InventarioEntity buscarporidmateriales(@Param("id") Integer id);

	@Query("select l from InventarioEntity l where l.id = :nombre")
	public List<InventarioEntity> buscarpornombre(@Param("nombre") String nombre);

	@Query("SELECT NEW com.example.demo.dto.InventarioDTO(" +
			"idm.id, " +
			"cat.cat, " +
			"cat.id, " +
			"idm.nom, " +
			"idm.area, " +
			"idm.cla, " +
			"idm.tocan, " +
			"idm.fere, " +
			"idm.pre, " +
			"idm.us, " +
			"idm.tipo, " +
			"idm.noser " +
			") " +
			"FROM InventarioEntity idm " +
			"JOIN idm.idseccat cat")
	public List<InventarioDTO> listarInventario();

	/*
	 * tipos de materiales:
	 * insumos
	 * herramientas -- estos vna a tener no_serie
	 * servicios
	 */

}
