package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.InventarioEntity;
import com.example.demo.entity.PeticionesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.PeticionesRepository;
import com.example.demo.repository.UserCorrectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.servicios.InventarioServicio;
import com.example.demo.servicios.UserServicio;



/*public class MenuPrincipalController { 
	
	@Autowired
	private UserServicio userServicio;	
	
	@Autowired
	private UserRepository repouser;
	
	@Autowired
	private UserCorrectRepository repousercorrect;
	
	@Autowired
	private InventarioServicio inventarioServicio;
	
	@Autowired
	private InventarioRepository repoIn;
	
	@Autowired
	private CategoryRepository repocate; 
	
	@Autowired 
	private PeticionesRepository repopeti;
	
  
	@GetMapping("/Principal")
	public String index(Model model) { 
		List<PeticionesEntity> peticion = repopeti.findAll();   
		model.addAttribute("peticiones",peticion); 
		return "InsertPeticion";
	}    
	
	@GetMapping("/newUser")
	public String index1us(Model model) { 
		List<UserEntity> usuario = repouser.findAll();  
	//	List<UserCorrect> usuariocorrecto = repousercorrect.findAll();
		model.addAttribute("usuarios",usuario); 
	//	model.addAttribute(" uariocorrectos", usuariocorrecto);
		return "insertUser";
	} 
	
	@GetMapping("/newregistroinventario")
	public String indexre(Model model) { 
		List<Category> categorias = repocate.findAll();  
		List<InventarioEntity> inventari = repoIn.findAll();
		model.addAttribute("categorias",categorias); 
		model.addAttribute("inventarios",inventari);
		return "Insertinventary";
		}  
	
	@GetMapping("/newCategory")
	public String indexca(Model model) { 
		List<Category> categorias = repocate.findAll(); 
		model.addAttribute("categorias",categorias); 
		return "InsertCategory";

}   
	@GetMapping("/newPeticion")
	public String indexpe(Model model) { 
		List<PeticionesEntity> peticion = repopeti.findAll();   
		model.addAttribute("peticiones",peticion); 
		return "InsertPeticion";
	}  
	
	
}
*/

@Controller
public class MenuPrincipalController { 
    
	@Autowired
	private UserServicio userServicio;	
	
	@Autowired
	private UserRepository repouser;
	
	@Autowired
	private UserCorrectRepository repousercorrect;
	
	@Autowired
	private InventarioServicio inventarioServicio;
	
	@Autowired
	private InventarioRepository repoIn;
	
	@Autowired
	private CategoryRepository repocate; 
	
	@Autowired 
	private PeticionesRepository repopeti;
	
    @GetMapping("/menuprincipal")
    public String menuPrincipal(Model model) {
        return "menu";  // Página principal del menú
    }

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
    	List<UserEntity> usuario = repouser.findAll();  
    	//	List<UserCorrect> usuariocorrecto = repousercorrect.findAll();
    		model.addAttribute("usuarios",usuario); 
    	//	model.addAttribute(" uariocorrectos", usuariocorrecto);
    		return "insertUser";  // Página de usuarios
    }

    @GetMapping("/inventario")
    public String verInventario(Model model) {
    	List<Category> categorias = repocate.findAll();  
		List<InventarioEntity> inventari = repoIn.findAll();
		model.addAttribute("categorias",categorias); 
		model.addAttribute("inventarios",inventari);
		return "Insertinventary";  // Página de inventario
    }

    @GetMapping("/categorias")
    public String verCategorias(Model model) {
    	List<Category> categorias = repocate.findAll(); 
		model.addAttribute("categorias",categorias); 
		return "InsertCategory"; // Página de categorías
    }

    @GetMapping("/listapeticiones")
    public String verPeticiones(Model model) {
    	List<PeticionesEntity> peticionactualizada = repopeti.findAll();
//    	List<Category> categoriaoficial = repocate.findAll();
    	model.addAttribute("peticiones", peticionactualizada);
//    	model.addAttribute("categorias",categoriaoficial);	
    	return "PeticionesList"; // Página de peticiones
    } 
    
    
    @GetMapping("/listausuarios")
	public String verlistarusuarios(Model model) {
	List<UserEntity> usuariosofi = repouser.findAll();
	model.addAttribute("usuarios",usuariosofi);
	return "UserList";
	}  
    
	@GetMapping("/listainvetariomaterialesregistrados")
	public String verlistarinventario(Model model) {
	List<InventarioEntity> materialoficial = repoIn.findAll();
//	List<Category> categoriaoficial = repocate.findAll();
	model.addAttribute("inventarios", materialoficial);
//	model.addAttribute("categorias",categoriaoficial);	
	return "InventaryList";
	} 
	
	  @GetMapping("/listacategorias")
		public String vercategorias(Model model) {
		List<Category> categoriaoficial = repocate.findAll();
		model.addAttribute("categorias",categoriaoficial);
		return "CatList";
		} 
	
    
}
