package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.UserCorrectRepository;

import com.example.demo.entity.UserCorrect;


public class UserCorrectController {
 
	@Autowired
	private UserCorrectRepository repo; 
	
	@GetMapping("/newUserCorret")
	public String index() {
		return "insertUserCorrect";
	}
		@PostMapping("addUserCorrect")
		public String login(@RequestParam("nom") String nom, 
			@RequestParam("ape") String apellido, Model model) {
			System.out.println("Nombre: " + nom);
			System.out.println("Nombre: " + apellido);
			
			if (!nom.isEmpty() && !apellido.isEmpty()) { 
				UserCorrect entitycorrect = new UserCorrect();
				entitycorrect.setNom(nom);
				entitycorrect.setApe(apellido); 
				model.addAttribute("mensaje", "Nombre del Usuario: " + nom + " creado correctamente!"); 
				return "insertUserCorrect";
			}
			model.addAttribute("mensaje", "Valores erroneos");
			return "insertUserCorrect";
			
	}
}
