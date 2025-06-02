package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.UserCorrect;
import com.example.demo.entity.UserEntity;
import com.example.demo.excepciones.MiException;
import com.example.demo.repository.UserCorrectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.servicios.UserServicio;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/usuarios")
public class UserController {
	@Autowired
	private UserServicio userServicio;
	@Autowired
	private UserRepository repouser;
	@Autowired
	private UserCorrectRepository repousercorrect;

	@GetMapping("/")
	public String index() {
		return "usuarios/usuarios";
	}

	@GetMapping(value = "/listar", name = "categoria.listar")
	@ResponseBody
	public ResponseEntity<?> Listar() {
		List<UsuarioDTO> lst = repouser.listars();
		return ResponseEntity.ok(lst);

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveRegistro(@RequestBody UsuarioDTO obj) {
		try {
			System.out.println(obj.toString());

			System.out.println(obj.getUsuarioId());

			if (obj.getUsuarioId() == null) {
				userServicio.crearUsuario(0, obj.getUsuario(), obj.getContrasena(), obj.getCorreoElectronico(),
						obj.getTipoId(), 0, obj.getNombre(), obj.getApellido());

			} else {

				System.out.println("entro en modificar");

				userServicio.modificardatosdeusuario(obj.getUsuarioId(), obj.getUsuario(), obj.getContrasena(),
						obj.getCorreoElectronico(), obj.getTipoId(), obj.getPersonaId(), obj.getNombre(),
						obj.getApellido());

			}

			return ResponseEntity.ok().body("Registro guardado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al guardar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<?> EliminarRegistro(@RequestBody UsuarioDTO obj) {

		try {

			userServicio.eliminar(obj.getUsuarioId());

			return ResponseEntity.ok().body("Registro eliminado correctamente");

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Error al eliminar el registro. Msj: " + e.getMessage() + e.getStackTrace());
		}

	}

	// @Transactional
	/*
	 * @PostMapping("addUser")
	 * public String login(@RequestParam("user") String usuario,
	 * 
	 * @RequestParam("pass") String pass,
	 * 
	 * @RequestParam("cor") String cor,
	 * 
	 * @RequestParam("tip") String tipousuario,
	 * 
	 * @RequestParam("nom") String nom,
	 * 
	 * @RequestParam("ape") String ape, Model model) {
	 * 
	 * System.out.println("Usuario: " + usuario);
	 * System.out.println("Contraseña: " + pass);
	 * System.out.println("Correo: " + cor);
	 * System.out.println("Tipo de Usuario: " + tipousuario);
	 * System.out.println("Nombre: " + nom);
	 * System.out.println("Apellidos: " + ape);
	 * 
	 * if (!usuario.isEmpty() && !pass.isEmpty()) {
	 * UserEntity entity = new UserEntity();
	 * entity.setUser(usuario);
	 * entity.setPass(pass);
	 * entity.setCor(cor);
	 * entity.setTip(tipousuario);
	 * 
	 * repouser.saveAndFlush(entity);
	 * 
	 * UserCorrect corentity = new UserCorrect();
	 * corentity.setNom(nom);
	 * corentity.setApe(ape);
	 * corentity.setUsen(entity);
	 * repousercorrect.saveAndFlush(corentity);
	 * 
	 * model.addAttribute("mensaje", "Usuario: " + usuario + " creado!");
	 * return index(model);
	 * // return "insertUser";
	 * }
	 * model.addAttribute("mensaje", "Valores erroneos");
	 * return "insertUser";
	 * }
	 * 
	 * @GetMapping("/listausuario")
	 * public String listar(Model model) {
	 * List<UserEntity> usuariosofi = repouser.findAll();
	 * model.addAttribute("usuarios", usuariosofi);
	 * return "UserList";
	 * }
	 * 
	 * /*
	 * 
	 * @GetMapping("/user/editar/{id}")
	 * public String editar(@PathVariable(name = "id") Integer id, Model model) {
	 * model.addAttribute("usuarios", UserServicio.getOne(id));
	 * return "EditUser";
	 * }
	 */
	/*
	 * @GetMapping("/user/editar/{id}")
	 * public String editar(@PathVariable(name = "id") Integer id, Model model) {
	 * Optional<UserEntity> usuarioofi = repouser.findById(id);
	 * model.addAttribute("usuario", usuarioofi.get());
	 * return "EditUser";
	 * }
	 * 
	 * @PostMapping("/user/editar/{id}")
	 * public String editar(
	 * 
	 * @PathVariable(name = "id") Integer idusu,
	 * 
	 * @RequestParam("user") String usuario,
	 * 
	 * @RequestParam("pass") String pass,
	 * 
	 * @RequestParam("cor") String cor,
	 * 
	 * @RequestParam("tip") String tipousuario,
	 * 
	 * @RequestParam("nom") String nom,
	 * 
	 * @RequestParam("ape") String ape, Model model) {
	 * 
	 * try {
	 * // userServicio.modificardatosdeusuario( idusu, usuario, pass, cor,
	 * tipousuario,
	 * // idusecor, nom, ape);
	 * UserEntity modiusuario = repouser.buscarporidusuario(idusu);
	 * modiusuario.setUser(usuario);
	 * modiusuario.setPass(pass);
	 * modiusuario.setCor(cor);
	 * modiusuario.setTip(tipousuario);
	 * 
	 * UserCorrect modicorentity = modiusuario.getUsercorr();
	 * modicorentity.setNom(nom);
	 * modicorentity.setApe(ape);
	 * modiusuario.setUsercorr(modicorentity);
	 * 
	 * repouser.saveAndFlush(modiusuario);
	 * 
	 * model.addAttribute("mensaje", "Usuario: " + usuario +
	 * " Editado Exitosamente!");
	 * 
	 * return listar(model);
	 * } catch (Exception ex) {
	 * model.addAttribute("error", ex.getMessage());
	 * return "EditUser";
	 * }
	 * }
	 * 
	 * @PostMapping("/user/eliminar/{id}")
	 * public String delete(@PathVariable(name = "id") Integer idusu, Model model) {
	 * Optional<UserEntity> usuarioofi = repouser.findById(idusu);
	 * 
	 * if (usuarioofi.isPresent()) {
	 * UserEntity eliminarusuario = usuarioofi.get();
	 * UserCorrect eliminarusuariocorrecto = eliminarusuario.getUsercorr();
	 * 
	 * repousercorrect.delete(eliminarusuariocorrecto);
	 * repouser.delete(eliminarusuario);
	 * }
	 * return listar(model);
	 * 
	 * }
	 * 
	 * @PostMapping("/user/buscar")
	 * public String search(@RequestParam("buscar") String user, Model model) {
	 * List<UserEntity> encotrarusuario =
	 * repouser.findByUserContainingIgnoreCase(user);
	 * 
	 * if (encotrarusuario.isEmpty()) {
	 * 
	 * return listar(model);
	 * }
	 * model.addAttribute("usuarios", encotrarusuario);
	 * return "UserList";
	 * 
	 * }
	 * 
	 * // recordatorio
	 */
}
