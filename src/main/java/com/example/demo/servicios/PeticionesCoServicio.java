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
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.PeticionesRepository;

import jakarta.transaction.Transactional;

@Service
public class PeticionesCoServicio {
	
	private static final Logger Log = LogManager.getLogger(PeticionesCoServicio.class);
	
	@Autowired 
	private PeticionesRepository peticionesRepository;
	
	@Autowired 
	private InventarioRepository inventarioRepository;
	
	@Transactional
	public void solicitarPeticiondeMterial(int id, String aresol, String nomsol, Integer cansol, String fepe, String espe, String feel, Integer cantidis) throws MiException  {
		validar(id,aresol,nomsol,cansol,fepe,espe,feel,cantidis); 
		
		PeticionesEntity peticionesEntity = peticionesRepository.findById(id).get();
		
		Optional<InventarioEntity> sorecuperarmaterialregistrado = inventarioRepository.findById(id);

		if (sorecuperarmaterialregistrado.isPresent()) { 
			
			PeticionesEntity peticionsolicitada = new PeticionesEntity();
			peticionsolicitada.setAresol(aresol);
			peticionsolicitada.setNomsol(nomsol);
			peticionsolicitada.setCansol(cansol);
			peticionsolicitada.setFepe(fepe);
			peticionsolicitada.setEspe(espe);
			peticionsolicitada.setFeel(feel);
			peticionsolicitada.setCantidis(cantidis);
			peticionesRepository.save(peticionsolicitada);
			
			Log.info("peticionid : {}", sorecuperarmaterialregistrado.get());
		}
	}
	
	public List<PeticionesEntity> solicitarmaterialmaterialregistrado(){
		
		List<PeticionesEntity> peticiones = new ArrayList();
		
		peticiones = peticionesRepository.findAll();
		
		return peticiones;
	}
	
	public void modificarpeticionsoliciado(int id, String aresol, String nomsol, Integer cansol, String fepe, 
			String espe, String feel, Integer cantidis)  throws MiException { 
		validar(id,aresol,nomsol,cansol,fepe,espe,feel,cantidis); 
		
		Optional<PeticionesEntity> respuestaPeticion = peticionesRepository.findById(id); 
		
		if(respuestaPeticion.isPresent()) {
			
			PeticionesEntity peticionsolicitada = respuestaPeticion.get();
			peticionsolicitada.setAresol(aresol);
			peticionsolicitada.setCansol(cansol);
			peticionsolicitada.setNomsol(nomsol);
			peticionsolicitada.setEspe(espe);
			peticionsolicitada.setFeel(feel);
			peticionsolicitada.setFepe(fepe);
			peticionsolicitada.setCantidis(cantidis);
			
		}	
	}
		
	private void validar(Integer id, String aresol, String nomsol, Integer cansol, String fepe, String espe, String feel,
			Integer cantidis) throws MiException {
	
		if(id == null) {
			throw new MiException("el id no puede ser nulo");
		}
	 
	 if(aresol.isEmpty() || aresol == null) {
			throw new MiException("el area solicitada no puede ser nulo o estar vacio");
		}
	 if(nomsol.isEmpty() || nomsol == null) {
			throw new MiException("el nombre del solicitante no puede ser nulo o estar vacio");
		}
	 if(cansol == null) {
			throw new MiException("la cantidad solicitada no puede ser nulo o estar vacio");
		}
	 if(fepe.isEmpty() || fepe == null) {
			throw new MiException("la fecha de peticion no puede ser nulo o estar vacio");
		} 
	 if(espe.isEmpty() || espe == null) {
			throw new MiException("la especificacion no puede ser nulo o estar vacio");
		}
	 if(feel.isEmpty() || feel == null) {
			throw new MiException("la fecha de elaboracion no puede ser nulo o estar vacio");
		}
	 if(cantidis == null) {
			throw new MiException("la cantidaddisponible no puede ser nulo o estar vacio");
		}	
		
	}
	
}
