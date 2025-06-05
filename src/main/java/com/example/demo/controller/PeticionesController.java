package com.example.demo.controller;

//import java.awt.PageAttributes.MediaType;
import java.io.IOException;
//import java.net.http.HttpHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.InventarioDTO;
import com.example.demo.dto.PeticionesDTO;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.PeticionesRepository;
import com.example.demo.servicios.InventarioServicio;
//import com.example.demo.servicios.PdfServicio;
import com.example.demo.utils.Global;
import com.example.demo.utils.ReporteTabularLogos;

import jakarta.persistence.Column;

@Controller
@RequestMapping("/peticiones")
public class PeticionesController {

	@Autowired
	private InventarioServicio inventarioServicio;

	@Autowired
	private InventarioRepository repoIn;

	@Autowired
	private CategoryRepository repocate;

	@Autowired
	private PeticionesRepository repopeti;

	@GetMapping("/index")
	public String indexPrecargado(@RequestParam("materialId") Integer materialId, Model model) {

		System.out.println("Nueva peticion");
		System.out.println(materialId);
		model.addAttribute("idRecibido", materialId);
		return "peticiones/peticiones";
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("materialId", 1);
		return "peticiones/peticiones";
	}


	@GetMapping(value = "/listar", name = "peticiones.listar")
	@ResponseBody
	public ResponseEntity<?> Listar() {
		List<PeticionesDTO> peticiones = repopeti.listarPeticiones();
		return ResponseEntity.ok(peticiones);

	}


@PostMapping("/save")
	public ResponseEntity<?> saveRegistro(@RequestBody PeticionesDTO obj) {
		try {

			System.out.println(obj.toString());

			int cansol = obj.getCantidadSolicitada();

			Optional<InventarioEntity> inventariopeticionactualizada = repoIn.findById(obj.getMaterialId());

			if (inventariopeticionactualizada.isPresent()) {
				InventarioEntity inventario = inventariopeticionactualizada.get();

				int cantidadDisponible = inventario.getTocan();

				System.out.println("Cantidad solicitada: "+cansol);
				System.out.println("Cantidad disponible: "+cantidadDisponible);

				/*if (cantidadDisponible >= cansol) {

					int resta = cantidadDisponible - cansol;

					inventario.setTocan(resta);
					repoIn.save(inventario);
				}*/

				int resta = cantidadDisponible - cansol;

				inventario.setTocan(resta);
				repoIn.save(inventario);

				PeticionesEntity objPet = new PeticionesEntity();

				objPet.setAresol(obj.getAreaSolicitada());
				objPet.setNomsol(obj.getNombreSolicitante());
				objPet.setCansol(obj.getCantidadSolicitada());
				
				objPet.setEspe(obj.getEspecificacion());
				LocalDate fecha = LocalDate.now();
				System.out.println(obj.getFechaPeticion());
				objPet.setFepe(obj.getFechaPeticion());
				objPet.setFeel(fecha.toString());
				objPet.setCantidis(obj.getCantidadDisponible());
				objPet.setTotalcan(inventario);

				repopeti.save(objPet);

			}
			

			return ResponseEntity.ok().body("Registro guardado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al guardar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<?> EliminarRegistro(@RequestBody PeticionesDTO obj)throws MiException {

		System.out.println(obj.getPeticionesId());

		try {

			//elimina y restaura la piezas tomadas

			PeticionesEntity objP = repopeti.findById(obj.getPeticionesId())
			.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + obj.getPeticionesId()));

			InventarioEntity objMaterial = repoIn.findById(objP.getTotalcan().getId())
			.orElseThrow(() -> new MiException("Registro no encontrado con ID: " + obj.getPeticionesId()));

			int cantidad = objMaterial.getTocan();

			cantidad = cantidad + objP.getCansol();

			objMaterial.setTocan(cantidad);

			repoIn.save(objMaterial);

			repopeti.delete(objP);
			

			return ResponseEntity.ok().body("Registro eliminado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al eliminar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}

	}



	 @Autowired
    private ReporteTabularLogos pdfService;

	@GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            byte[] pdf = pdfService.generatePdf();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "documento.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdf);
                    
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }





}