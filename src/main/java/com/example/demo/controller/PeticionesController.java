package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.PeticionesRepository;
import com.example.demo.servicios.InventarioServicio;
//import com.example.demo.servicios.PdfServicio;

import jakarta.persistence.Column;

@Controller
public class PeticionesController {
	
	@Autowired
	private InventarioServicio inventarioServicio;
	
	@Autowired
	private InventarioRepository repoIn;
	
	@Autowired
	private CategoryRepository repocate;
	
	@Autowired 
	private PeticionesRepository repopeti;
	
	//@Autowired
	//private PdfServicio pdfServicio;
	
	@GetMapping("/newPeticion")
	public String index(Model model) { 
		List<PeticionesEntity> peticion = repopeti.findAll();   
		model.addAttribute("peticiones",peticion); 
		return "InsertPeticion";
	}   
	
	@GetMapping("/nombre/peticion/addpeticion")
	public String handleGetPeticion(Model model) {
	    model.addAttribute("mensaje", "Por favor, envía el formulario usando POST.");
	    return "InsertPeticion";
	}
	
	
	@PostMapping(value = "/nombre/peticion/addpeticion")
	public String peticion(@RequestParam("id") Integer idmain,
			               @RequestParam("nombre_del_solicitante") String nomsol,
			               @RequestParam("area_solicitada") String aresol,	                  
		                   @RequestParam("cantidad_solicitada") Integer cansol,
		                   @RequestParam("fecha_de_peticion") String fepe,
		                   @RequestParam("especificacion") String espe,
		                   @RequestParam("fecha_de_elaboracion") String feel,        
		                   Model model) {
		 
		
			System.out.println("Area_solicitada: " + aresol);
			System.out.println("Nombre_del_solicitante: " + nomsol);
			System.out.println("Cantidad_solicitada: " + cansol);
			System.out.println("Fecha_de_peticion: " + fepe);
			System.out.println("Especificacion: " + espe);
			System.out.println("Fecha_de_elaboracion: " + feel);
			
		
		  //Optional<InventarioEntity> inventariopeticionactualizada = InventarioRepository.findById(idmain);
			Optional<InventarioEntity> inventariopeticionactualizada = repoIn.findById(idmain);
			 
			  
			  if (inventariopeticionactualizada.isPresent()) {
		            InventarioEntity inventario = inventariopeticionactualizada.get();

		            int cantidadDisponible = inventario.getTocan();

		            if (cantidadDisponible >= cansol) {
		            	
		             int resta = cantidadDisponible - cansol;
		             
		                inventario.setTocan(resta);
		                repoIn.save(inventario);

		                PeticionesEntity nuevaPeticion = new PeticionesEntity();
		                nuevaPeticion.setAresol(aresol);
		                nuevaPeticion.setNomsol(nomsol);
		                nuevaPeticion.setCansol(cansol);
		                nuevaPeticion.setFepe(fepe);
		                nuevaPeticion.setEspe(espe);
		                nuevaPeticion.setFeel(feel);
		                nuevaPeticion.setCantidis(resta);
		                
		                nuevaPeticion.setTotalcan(inventario);

		                repopeti.save(nuevaPeticion);  
		                
		                model.addAttribute("mensaje", "Petición agregada correctamente.");
		                //return "redirect:/nombre/buscar";
		                return "redirect:/listadeeptcionesactualizables";
		          } else {
		              model.addAttribute("mensaje", "No hay suficiente inventario disponible.");
		              return "InsertPeticion";
		          }  
			  } else {
			        // Si el inventario no existe, maneja la situación
			        model.addAttribute("mensaje", "Inventario no encontrado.");
			        return "InsertPeticion";
			    } 
		    }


			
		/*	if (!aresol.isEmpty() && !nomsol.isEmpty()) {
				PeticionesEntity PeticionesEntity = new PeticionesEntity();
				PeticionesEntity.setAresol(aresol);
				PeticionesEntity.setNomsol(nomsol);
				PeticionesEntity.setCansol(cansol);
				PeticionesEntity.setFepe(fepe);
				PeticionesEntity.setEspe(espe);
				PeticionesEntity.setFeel(feel);
				
				
				repopeti.saveAndFlush(PeticionesEntity);
				
				model.addAttribute("mensaje", "Registro de: " + cansol + " agregada!");
				return index(model);
				
			}
			model.addAttribute("mensaje", "Valores erroneos");
			return "InsertPeticion";
	}*/
	
 
	/*@GetMapping("/peticionesyactualizaciones")
	public String listarinventario(Model model) {
	List<InventarioEntity> materialoficial = repoIn.findAll();
//	List<Category> categoriaoficial = repocate.findAll();
	model.addAttribute("inventarios", materialoficial);
//	model.addAttribute("categorias",categoriaoficial);	
	return "InventaryListPeticActual";  
	
	}*/
	
	@GetMapping("/listadeeptcionesactualizables")
	public String listarptcionesactualizables(Model model) {
	List<PeticionesEntity> peticionactualizada = repopeti.findAll();
//	List<Category> categoriaoficial = repocate.findAll();
	model.addAttribute("peticiones", peticionactualizada);
//	model.addAttribute("categorias",categoriaoficial);	
	return "PeticionesList";
	} 
	
	  @PostMapping("/peticion/buscar")
	  public String search (@RequestParam("buscar")String nomsol, Model model) {
		  List<PeticionesEntity> encotrarpeticion = repopeti.findByNomsolContainingIgnoreCase(nomsol);
		   
		  if(encotrarpeticion.isEmpty()) {
			  
			  return listarptcionesactualizables(model);
		  }
		  model.addAttribute("inventarios",  encotrarpeticion);
		  return "PeticionesList";
	  }
	
	  @PostMapping("/peticion/eliminar/{id}")
		 public String delete(@PathVariable(name = "id") Integer idpeti, Model model) {
			 Optional<PeticionesEntity> peticionoficial = repopeti.findById(idpeti);
			 
			 
			 if(peticionoficial.isPresent()) {
				 PeticionesEntity eliminarpeticion = peticionoficial.get(); 
				 repopeti.delete(eliminarpeticion);
				
				 
			 }
			return listarptcionesactualizables(model);
	  } 
	  /*@GetMapping("/peticion/reporte/{id}")
	    public ResponseEntity<byte[]> generarReportePdf(@PathVariable("id") Integer id) {
	        Optional<PeticionesEntity> optionalPeticion = repopeti.findById(id);

	        if (optionalPeticion.isPresent()) {
	            PeticionesEntity peticion = optionalPeticion.get();

	            try {
	                byte[] pdfBytes = pdfServicio.generarReportePDF(peticion);

	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.APPLICATION_PDF);
	                headers.setContentDispositionFormData("filename", "reporte_peticion_" + id + ".pdf");

	                return ResponseEntity.ok()
	                        .headers(headers)
	                        .body(pdfBytes);

	            } catch (IOException e) {
	                e.printStackTrace();
	                return ResponseEntity.internalServerError().build();
	            }

	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }*/
  }
	



