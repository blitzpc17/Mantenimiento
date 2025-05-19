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
@Table(name = "administrador_autorizado")
public class UserCorrect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idautorizado")
	private Integer id;
	
	@Column(name = "nombre")
	private String nom;

	@Column(name = "apellido")
	private String ape; 
	 
    @OneToOne
    @JoinColumn(name = "id_usuarios_admin")
    private UserEntity usen; 
    
   /* @OneToOne
	@JoinColumn(name = "id_usuarios_admin")
	private UserEntity idusuariosadm; */
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	public UserEntity getUsen() {
		return usen;
	}

	public void setUsen(UserEntity usen) {
		this.usen = usen;
	}
	

}

