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
import com.example.demo.entity.UserCorrect;
import com.example.demo.entity.UserEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
public class InventarioServicio {

	private static final Logger log = LogManager.getLogger(InventarioServicio.class);
	@Autowired
	private InventarioRepository inventarioRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public void crearRegistroInventario(int id, String nom, String cla, Integer tocan, String fere, Float pre,
			String us, Integer cat, String area, int tip, String noSer) throws MiException {

		validar(id, nom, cla, tocan, fere, pre, us, area);
		// InventarioEntity inventarioEntity = inventarioRepository.findById(id).get();
		Category recuperarcategoria = categoryRepository.buscarporidcategorias(cat);
		// Optional<Category> recuperarCategoria = categoryRepository.findById(id);

		// if (recuperarCategoria.isPresent()) {
		InventarioEntity registromaterial = new InventarioEntity();

		registromaterial.setNom(nom);
		registromaterial.setCla(cla);
		registromaterial.setTocan(tocan);
		registromaterial.setFere(fere);
		registromaterial.setPre(pre);
		registromaterial.setUs(us);
		registromaterial.setArea(area);
		registromaterial.setIdseccat(recuperarcategoria/* .get() */);
		registromaterial.setNoser(noSer);
		registromaterial.setTipo(tip);
		inventarioRepository.save(registromaterial);

		log.info("materialid : {}", registromaterial.getId());

		// Category categoriaregostrada = new Category();
		// categoriaregostrada.setCat(cat);

	}

	public List<InventarioEntity> listarregistrodeinventario() {

		List<InventarioEntity> invetarios = new ArrayList();

		invetarios = inventarioRepository.findAll();

		return invetarios;
	}

	public void modificarregistrodeinventario(Integer id, String nom, String cla, Integer tocan, String fere, float pre,
			String us, String area, String serie, Integer tipo) throws MiException {

		validar(id, nom, cla, tocan, fere, pre, us, area);

		InventarioEntity registrodematerial = inventarioRepository.findById(id)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + id));

		registrodematerial.setNom(nom);
		registrodematerial.setCla(cla);
		registrodematerial.setTocan(tocan);
		registrodematerial.setFere(fere);
		registrodematerial.setPre(pre);
		registrodematerial.setUs(us);
		registrodematerial.setArea(area);
		registrodematerial.setNoser(serie);
		registrodematerial.setTipo(tipo);

		inventarioRepository.save(registrodematerial);

	}

	private void validar(Integer id, String nom, String cla, Integer tocan, String fere, Float pre, String us,
			String area) throws MiException {

		if (id == null) {
			throw new MiException("el id no puede ser nulo");
		}

		if (nom.isEmpty() || nom == null) {
			throw new MiException("el nombre no puede ser nulo o estar vacio");
		}
		if (cla.isEmpty() || cla == null) {
			throw new MiException("la clave no puede ser nulo o estar vacio");
		}
		if (tocan == null) {
			throw new MiException("la cantidad total no puede ser nulo o estar vacio");
		}
		if (fere.isEmpty() || fere == null) {
			throw new MiException("la fecha de registro no puede ser nulo o estar vacio");
		}
		if (pre == null) {
			throw new MiException("el precio no puede ser nulo o estar vacio");
		}
		if (us.isEmpty() || us == null) {
			throw new MiException("el uso no puede ser nulo o estar vacio");
		}
		if (area.isEmpty() || area == null) {
			throw new MiException("el area no puede ser nulo o estar vacio");
		}

	}

	@Transactional
	public void eliminar(int id) throws MiException {

		InventarioEntity registrodematerial = inventarioRepository.findById(id)
				.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + id));

		inventarioRepository.delete(registrodematerial);
	}

}
