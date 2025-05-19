package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserCorrect;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByCatContainingIgnoreCase(String cat);

	@Query("select l from Category l where l.id = :id")
	public Category buscarporidcategorias(@Param("id") Integer id);

	@Query("select l from Category l where l.id = :categoria")
	public List<Category> buscarporcategoria(@Param("categoria") String categoria);

	@Query("SELECT NEW com.example.demo.dto.CategoriaDTO ("
			+ "c.id, "
			+ "c.cat) FROM Category c")
	public List<CategoriaDTO> listarcategorias();

}
