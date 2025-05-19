package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuarios")
	private Integer id;
	
	
	@Column(name = "usuario")
	private String user;

	@Column(name = "contrasena")
	private String pass; 
	
	@Column(name = "correo")
	private String cor;
	
	@Column(name = "tipousuario")
	private String tip;
	
	 
	@OneToOne(mappedBy = "usen")
	private UserCorrect usercorr;
	
	
	/*@OneToOne(mappedBy = "idusuariosadmin")
	private List<UserCorrect> usencor;
    
    public List<UserCorrect> getUsencor() {
		return usencor;
	}

	public void setUsencor(List<UserCorrect> usencor) {
		this.usencor = usencor;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getCor() {
	   return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
		
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
		
	}

	public UserCorrect getUsercorr() {
		return usercorr;
	}

	public void setUsercorr(UserCorrect usercorr) {
		this.usercorr = usercorr;
	}
	
}
