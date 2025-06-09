package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserCorrectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.servicios.UserServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController { 
	
	@Autowired
	private UserRepository repouser;
	@Autowired
	private UserCorrectRepository repousercorrect;
	
	@Autowired
	private UserServicio userServicio;	

	@GetMapping("/accesslogin")
	public String index() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(@RequestParam("user") String usuario,
			            @RequestParam("pass") String pass,
			            Model model, HttpSession session) {
		System.out.println("Usuario: " + usuario);
		System.out.println("Contraseña: " + pass);

		   Optional<UserEntity> user = userServicio.autenticar(usuario, pass);
		   if (user.isPresent()) {
			    session.setAttribute("usuario", user.get());
	            model.addAttribute("mensaje", "Credenciales correctas");
	            return "redirect:/menuprincipal";
	        } else {
	            model.addAttribute("mensaje", "Credenciales incorrectas");
	            return "login";
	        }
	    

} 
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/accesslogin";
	    }
	 @GetMapping("/recuperar-contrasena")
	 public String mostrarRecuperacion() {
	     return "RecuperarContrasena";  // Página de recuperación
	 }

	 @PostMapping("/recuperar-contrasena")
	 public ResponseEntity<?> recuperarContrasena(@RequestBody UsuarioDTO obj) {

		System.out.println(obj.toString());

		List<UserEntity>lst =  repouser.buscarPorKeywordSQL(obj.getNombre(), obj.getApellido(), obj.getUsuario(), obj.getCorreoElectronico());

		if(lst.size()>0){

			try{

				UserEntity objR = lst.get(0);

				userServicio.modificarPassword(objR.getId(), obj.getContrasena());

				return ResponseEntity.ok().body(true);

			}catch(Exception ex){
				return ResponseEntity.ok().body(false);
			}		

		}		
		
		return ResponseEntity.ok().body(false);
	 }
}
	/*
	@Controller
	public class LoginController {

		@GetMapping("/")
		public String index() {
			return "login";
		}
		
		@PostMapping("login")
		public String login(@RequestParam("user") String usuario,
				@RequestParam("pass") String pass,
				@RequestParam("cor") String cor,
				@RequestParam("tip") String tip, Model model) {
			System.out.println("Usuario: " + usuario);
			System.out.println("Contraseña: " + pass);
			System.out.println("Correo: " + cor);
			System.out.println("Tipo de usuario: " + tip);
			if (!usuario.isEmpty() && !pass.isEmpty()) {
				model.addAttribute("mensaje", "credenciales correctas");
				return "principal";
			}
			model.addAttribute("mensaje", "credenciales incorrectas");
			return "login";
		}
	}*/

