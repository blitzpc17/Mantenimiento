package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.InventarioDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.servicios.CategoryServicios;
import com.example.demo.utils.Global;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

	@Autowired
	private CategoryRepository repocate;
	@Autowired
	private CategoryServicios catService;

	/*
	 * 
	 * @GetMapping("/newCategory")
	 * public String index(Model model) {
	 * List<Category> categorias = repocate.findAll();
	 * model.addAttribute("categorias", categorias);
	 * return "InsertCategory";
	 * 
	 * }
	 * 
	 * @GetMapping("/addCategory")
	 * public String mostrarFormulario(Model model) {
	 * model.addAttribute("categorias", repocate.findAll());
	 * return "InsertCategory"; // Asegúrate de que este archivo exista en
	 * templates/
	 * }
	 * 
	 * @PostMapping("addCategory")
	 * public String category(@RequestParam("cat") String categoria, Model model) {
	 * System.out.println("Categoria: " + categoria);
	 * 
	 * if (!categoria.isEmpty()) {
	 * Category catentity = new Category();
	 * catentity.setCat(categoria);
	 * repocate.save(catentity);
	 * 
	 * model.addAttribute("mensaje", "Categoria: " + categoria +
	 * " creada correctamente!");
	 * return "InsertCategory";
	 * }
	 * model.addAttribute("mensaje", "Valores erroneos");
	 * return "InsertCategory";
	 * 
	 * }
	 * 
	 * @GetMapping("/listacategoria")
	 * public String listarcat(Model model) {
	 * List<Category> categoriaoficial = repocate.findAll();
	 * model.addAttribute("categorias", categoriaoficial);
	 * return "CatList";
	 * }
	 * 
	 * @GetMapping("/cat/editar/{id}")
	 * public String editar(@PathVariable(name = "id") Integer id, Model model) {
	 * Optional<Category> categoriaoficial = repocate.findById(id);
	 * model.addAttribute("categorias", categoriaoficial.get());
	 * return "EditCategory";
	 * 
	 * }
	 * 
	 * @PostMapping("/cat/editar/{id}")
	 * public String editar(@PathVariable(name = "id") Integer idcat,
	 * 
	 * @RequestParam("cat") String categoria, Model model) {
	 * try {
	 * Category modicategory = repocate.buscarporidcategorias(idcat);
	 * modicategory.setCat(categoria);
	 * repocate.saveAndFlush(modicategory);
	 * 
	 * model.addAttribute("mensaje", "Categoria: " + categoria +
	 * " Editada Exitosamente!");
	 * 
	 * return listarcat(model);
	 * } catch (Exception ex) {
	 * model.addAttribute("error", ex.getMessage());
	 * return "EditCategory";
	 * }
	 * }
	 * 
	 * @PostMapping("/cat/eliminar/{id}")
	 * public String delete(@PathVariable(name = "id") Integer idcat, Model model) {
	 * Optional<Category> categoriaofi = repocate.findById(idcat);
	 * 
	 * if (categoriaofi.isPresent()) {
	 * Category eliminarcategoria = categoriaofi.get();
	 * repocate.delete(eliminarcategoria);
	 * }
	 * return listarcat(model);
	 * }
	 * 
	 * @PostMapping("/cat/buscar")
	 * public String search(@RequestParam("buscar") String cat, Model model) {
	 * List<Category> encontrarcategoria =
	 * repocate.findByCatContainingIgnoreCase(cat);
	 * 
	 * if (encontrarcategoria.isEmpty()) {
	 * return listarcat(model);
	 * 
	 * }
	 * model.addAttribute("categorias", encontrarcategoria);
	 * return "CatList";
	 * }
	 * 
	 */

	/** nuevo by el mayo */

	@GetMapping("/")
	public String index() {
		// TODO: process POST request

		return "categorias/categorias";
	}

	@GetMapping(value = "/listar", name = "categoria.listar")
	@ResponseBody
	public ResponseEntity<?> Listar() {
		List<CategoriaDTO> categoriaoficial = repocate.listarcategorias();
		return ResponseEntity.ok(categoriaoficial);

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveRegistro(@RequestBody CategoriaDTO obj) {
		try {
			System.out.println(obj.toString());

			System.out.println(obj.getIdCategoria());

			if (obj.getIdCategoria() == null) {

				catService.crearRegistroInventario(0, obj.getNombre());

			} else {

				catService.modificarregistro(obj.getIdCategoria(), obj.getNombre());

			}

			return ResponseEntity.ok().body("Registro guardado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al guardar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<?> EliminarRegistro(@RequestBody CategoriaDTO obj) {

		try {

			catService.eliminar(obj.getIdCategoria());

			return ResponseEntity.ok().body("Registro eliminado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al eliminar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}

	}

}
