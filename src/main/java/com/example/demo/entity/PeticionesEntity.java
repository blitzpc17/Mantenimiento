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
import jakarta.persistence.Table;

@Entity
@Table(name = "peticiones_solicitadas")
public class PeticionesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpeticiones")
	private Integer id;
	
	@Column(name = "area_solicitada")
	private String aresol; 
	
	@Column(name = "nombre_del_solicitante")
	private String nomsol;  
	
	@Column(name = "cantidad_solicitada")
	private Integer cansol; 
	
	@Column(name = "fecha_de_peticion")
	private String fepe; 
	
	@Column(name = "especificacion")
	private String espe;  
	
	@Column(name = "fecha_de_elaboracion")
	private String feel;  
	
	@Column(name = "cantidad_disponible")
	private Integer cantidis; 
	
	@ManyToOne
	@JoinColumn(name = "inventario_id")
	private InventarioEntity totalcan;  
	
	public Integer getId() {
		return id;
	}

	public InventarioEntity getTotalcan() {
		return totalcan;
	}

	public void setTotalcan(InventarioEntity totalcan) {
		this.totalcan = totalcan;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAresol() {
		return aresol;
	}

	public void setAresol(String aresol) {
		this.aresol = aresol;
	}
	
	public String getNomsol() {
		return nomsol;
	}

	public void setNomsol(String nomsol) {
		this.nomsol = nomsol;
	}
	
	public Integer getCansol() {
		return cansol;
	}

	public void setCansol(Integer cansol) {
		this.cansol = cansol;
	} 
	
	public String getFepe() {
		return fepe;
	}

	public void setFepe(String fepe) {
		this.fepe = fepe;
	}  
	
	public String getEspe() {
		return espe;
	}

	public void setEspe(String espe) {
		this.espe = espe;
	} 
	
	public String getFeel() {
		return feel;
	}

	public void setFeel(String feel) {
		this.feel = feel;
	} 
	
	public Integer getCantidis() {
		return cantidis;
	}

	public void setCantidis(Integer cantidis) {
		this.cantidis = cantidis;
	}

	
}
