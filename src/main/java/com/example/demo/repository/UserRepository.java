package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByUserContainingIgnoreCase(String user);

	@Query("select l from UserEntity l where l.id = :usuario")
	public UserEntity buscarporidusuario(@Param("usuario") Integer id);

	@Query("select l from UserEntity l where l.id = :usuario")
	public List<UserEntity> buscarporusuario(@Param("usuario") String usuario);

	@Query("SELECT u FROM UserEntity u WHERE LOWER(u.user) = LOWER(:user)")
	Optional<UserEntity> findByUserIgnoreCase(@Param("user") String user);

	@Query("SELECT NEW com.example.demo.dto.UsuarioDTO ("
			+ "c.id, "
			+ "a.nom, "
			+ "a.ape, "
			+ "c.cor, "
			+ "c.tip, "
			+ "c.user, "
			+ "c.pass, "
			+ "a.id"
			+ ") FROM UserCorrect a "
			+ "JOIN a.usen c ")
	public List<UsuarioDTO> listars();

}
