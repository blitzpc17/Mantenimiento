package com.example.demo.servicios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActualizacionRegisterEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.ActualizacionRegisterRepository;
import com.mysql.cj.log.Log;

import jakarta.transaction.Transactional;

@Service
public class ActualizacionRegisterEntityServicios {
 
	private static final Logger log = LogManager.getLogger(ActualizacionRegisterEntityServicios.class);
	
	@Autowired
	private ActualizacionRegisterRepository actualizacionRegisterRepository;
    
	@Transactional
	public void crearActualizacionInventario( int id, String actualizacion) throws MiException  { 
		
		validad(id,actualizacion);
		ActualizacionRegisterEntity actualizacionRegisterEntity = actualizacionRegisterRepository.findById(id).get();
		
		ActualizacionRegisterEntity actualizarCantidad = new ActualizacionRegisterEntity(); 
		
		actualizarCantidad.setcanocu(actualizacion);
		
		actualizacionRegisterRepository.save(actualizarCantidad);
		
		log.info("actualizarcantidad : {}",actualizarCantidad.getId() );
	}
	
	// public List<ActualizacionRegisterEntity>
	

	private void validad(Integer id, String actualizacion) {
		// TODO Auto-generated method stub
		
	}
}

