/*package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActualizacionRegisterEntity;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.ActualizacionRegisterRepository;
import com.example.demo.repository.PeticionesRepository;

import jakarta.transaction.Transactional;

@Service
public class PeticionesServicio {

	private static final Logger Log = LogManager.getLogger(PeticionesServicio.class);
	
	@Autowired 
	private PeticionesRepository peticionesRepository;
	
	@Autowired 
	private ActualizacionRegisterRepository actualizacionRegisterRepository; 
	
	@Transactional
	public void solicitarPeticiondeMterial(int id, String aresol, String nomsol, String cansol, 
			String fepe, String espe, String feel)  throws MiException {
		validar(id,aresol,nomsol,cansol,fepe,espe,feel);
		PeticionesEntity peticionesEntity = peticionesRepository.findById(id).get();
	
		Optional<ActualizacionRegisterEntity> recuperarcantidad = actualizacionRegisterRepository.findById(id);
		
		if (recuperarcantidad.isPresent()) { 
			
			PeticionesEntity peticionpedida = new PeticionesEntity();
			peticionpedida.setAresol(aresol);
			peticionpedida.setNomsol(nomsol);
			peticionpedida.setCansol(cansol);
			peticionpedida.setFepe(fepe);
			peticionpedida.setEspe(espe);
			peticionpedida.setFeel(feel); 
		//	peticionpedida.setIdactuaregis(recuperarcantidad.get());
			peticionesRepository.save(peticionpedida);
			
			Log.info("peticionid : {}", peticionpedida.getId());
		}
	}
	public List<PeticionesEntity> solicitarmaterialmaterialregistrado(){
		
		List<PeticionesEntity> peticiones = new ArrayList();
		
		peticiones = peticionesRepository.findAll();
		
		return peticiones;
	}
	public void modificarpeticionsoliciado(int id, String aresol, String nomsol, String cansol, 
			String fepe, String espe, String feel)  throws MiException {
		validar(id,aresol,nomsol,cansol,fepe,espe,feel);
		Optional<PeticionesEntity> respuestaPeticion = peticionesRepository.findById(id); 
		
		if(respuestaPeticion.isPresent()) {
			
			PeticionesEntity peticionsolicitada = respuestaPeticion.get();
			peticionsolicitada.setAresol(aresol);
			peticionsolicitada.setCansol(cansol);
			peticionsolicitada.setNomsol(nomsol);
			peticionsolicitada.setEspe(espe);
			peticionsolicitada.setFeel(feel);
			peticionsolicitada.setFepe(fepe);
					
		}
	}
	private void validar(Integer id, String aresol, String nomsol, String cansol, String fepe, String espe, String feel) throws MiException{
	   
		 if(id == null) {
				throw new MiException("el id no puede ser nulo");
			}
		 
		 if(aresol.isEmpty() || aresol == null) {
				throw new MiException("el area solicitada no puede ser nulo o estar vacio");
			}
		 if(nomsol.isEmpty() || nomsol == null) {
				throw new MiException("el nombre del solicitante no puede ser nulo o estar vacio");
			}
		 if(cansol.isEmpty() || cansol == null) {
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
		
	}
}*/



