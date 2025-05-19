package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actualizacion_registros_disponibles")
public class ActualizacionRegisterEntity {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cantidad_ocupada")
	private String canocu; 
	
	@Column(name = "idmateriales")
	private Integer idma; 
	
	@Column(name = "idpeticiones")
	private Integer idpe; 
	

	/*@OneToMany(mappedBy = "idactuaregis")
	private List<PeticionesEntity> actual; 
	
	public List<PeticionesEntity> getActual() {
		return actual;
	 }
	
	public void setActual(List<PeticionesEntity> actual) {
		this.actual = actual;
	 }*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		
	}
	public String getCanocu() {
	      return canocu;
		}

	public void setcanocu(String canocu) {
			this.canocu = canocu;
		}
	
	public Integer getIdma() {
		return idma;
	}

	public void setIdma(Integer idma) {
		this.idma = idma;
		
	}
	
	public Integer getIdpe() {
		return idpe;
	}

	public void setIdpe(Integer idpe) {
		this.idpe = idpe;
		
	}	
	public void setCanocu(String canocu) {
		this.canocu = canocu;
	}
	
}
