package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryServicios {

	private static final Logger log = LogManager.getLogger(CategoryServicios.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public void crearRegistroInventario(int id, String categoria) throws MiException {

		// validar(id, categoria);
		// Category category = categoryRepository.findById(id).get();

		Category registroCategoria = new Category();

		// registroCategoria.setCat(categoria);
		registroCategoria.setCat(categoria);

		categoryRepository.save(registroCategoria);

		log.info("registrocategoria : {}", registroCategoria.getId());

	}

	public List<Category> listarporcategoriao() {

		List<Category> categoriasoficiales = new ArrayList();

		categoriasoficiales = categoryRepository.findAll();

		return categoriasoficiales;

	}

	public void modificarregistro(int idcat, String categoria) throws MiException {

		validar(idcat, categoria);

		Category registro = categoryRepository.findById(idcat)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + idcat));

		registro.setCat(categoria);

		categoryRepository.save(registro);

	}

	@Transactional
	public void eliminar(int id) throws MiException {

		Category registrodematerial = categoryRepository.findById(id)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + id));

		categoryRepository.delete(registrodematerial);
	}

	private void validar(Integer id, String categoria) throws MiException {

		if (id == null) {
			throw new MiException("el id no puede ser nulo");
		}
		if (categoria.isEmpty() || categoria == null) {
			throw new MiException("la categoria no puede ser nulo o estar vacio");
		}

	}
}
