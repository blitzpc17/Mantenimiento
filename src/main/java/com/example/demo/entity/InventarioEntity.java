package com.example.demo.entity;

import java.util.Date;
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
@Table(name = "inventario_de_materiales")
public class InventarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmateriales")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idseccion")
	private Category idseccat;  
	
	@OneToMany(mappedBy = "totalcan")
	private List<PeticionesEntity> peticoneslist; 
	
	@Column(name = "nombre")
	private String nom; 
	
	@Column(name = "clave")
	private String cla;
	
	@Column(name = "total_cantidad")
	private Integer tocan;
	
	@Column(name = "fecha_registro")
	private String fere;
	
	@Column(name = "precio")
	private Float pre; 
	
	@Column(name = "uso")
	private String us; 
	
	@Column(name = "area")
	private String area; 

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "no_serie")
	private String noser;
	
	
	/*@ManyToOne
	@JoinColumn(name = "idactualizacion_registros")
	private ActualizacionRegisterEntity idactuaregis; 
	
	public ActualizacionRegisterEntity getIdactuaregis() {
		return idactuaregis;
	}

	public void setIdactuaregis(ActualizacionRegisterEntity idactuaregis) {
		this.idactuaregis = idactuaregis;
	}*/
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Category getIdseccat() {
		return idseccat;
	}

	public void setIdseccat(Category idseccat) {
		this.idseccat = idseccat;
	} 


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}  
	
	public Integer getTocan() {
		return tocan;
	}

	public void setTocan(Integer tocan) {
		this.tocan = tocan;
	} 
	
	public String getFere() {
		return fere;
	}

	public void setFere(String fere) {
		this.fere = fere;
	}
	
	public Float getPre() {
		return pre;
	}

	public void setPre(Float pre) {
		this.pre = pre;
	}
	
	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}
	
	public String getArea() {
		return area;		
	}
	public void setArea(String area) {
		this.area = area;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNoser() {
		return noser;
	}

	public void setNoser(String noSer) {
		this.noser = noSer;
	}





}

