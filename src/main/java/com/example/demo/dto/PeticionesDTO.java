package com.example.demo.dto;

public class PeticionesDTO {

    private Integer peticionesId;
    private String areaSolicitada;
    private String nombreSolicitante;
    private Integer cantidadSolicitada;
    private String fechaPeticion;
    private String especificacion;
    private String fechaElaboracion;
    private Integer cantidadDisponible;
    private Integer materialId;
    private String claveMaterial;
    private String nombreMaterial;
    private String usoMaterial;
    private Float precioUnitario;
    
    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public PeticionesDTO() {

    }

    public PeticionesDTO(Integer peticionesId, String areaSolicitada, String nombreSolicitante,
            Integer cantidadSolicitada, String fechaPeticion, String especificacion, String fechaElaboracion,
            Integer cantidadDisponible, Integer materialId, String claveMaterial, String nombreMaterial,
            String usoMaterial, Float precioUnitario) {
        this.peticionesId = peticionesId;
        this.areaSolicitada = areaSolicitada;
        this.nombreSolicitante = nombreSolicitante;
        this.cantidadSolicitada = cantidadSolicitada;
        this.fechaPeticion = fechaPeticion;
        this.especificacion = especificacion;
        this.fechaElaboracion = fechaElaboracion;
        this.cantidadDisponible = cantidadDisponible;
        this.materialId = materialId;
        this.claveMaterial = claveMaterial;
        this.nombreMaterial = nombreMaterial;
        this.usoMaterial = usoMaterial;
        this.precioUnitario = precioUnitario;
    }
    public Integer getPeticionesId() {
        return peticionesId;
    }
    public void setPeticionesId(Integer peticionesId) {
        this.peticionesId = peticionesId;
    }
    public String getAreaSolicitada() {
        return areaSolicitada;
    }
    public void setAreaSolicitada(String areaSolicitada) {
        this.areaSolicitada = areaSolicitada;
    }
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }
    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }
    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }
    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }
    public String getFechaPeticion() {
        return fechaPeticion;
    }
    public void setFechaPeticion(String fechaPeticion) {
        this.fechaPeticion = fechaPeticion;
    }
    public String getEspecificacion() {
        return especificacion;
    }
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }
    public String getFechaElaboracion() {
        return fechaElaboracion;
    }
    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public Integer getMaterialId() {
        return materialId;
    }
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    public String getClaveMaterial() {
        return claveMaterial;
    }
    public void setClaveMaterial(String claveMaterial) {
        this.claveMaterial = claveMaterial;
    }
    public String getNombreMaterial() {
        return nombreMaterial;
    }
    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
    public String getUsoMaterial() {
        return usoMaterial;
    }
    public void setUsoMaterial(String usoMaterial) {
        this.usoMaterial = usoMaterial;
    }

    @Override
    public String toString() {
        return "PeticionesDTO [peticionesId=" + peticionesId + ", areaSolicitada=" + areaSolicitada
                + ", nombreSolicitante=" + nombreSolicitante + ", cantidadSolicitada=" + cantidadSolicitada
                + ", fechaPeticion=" + fechaPeticion + ", especificacion=" + especificacion + ", fechaElaboracion="
                + fechaElaboracion + ", cantidadDisponible=" + cantidadDisponible + ", materialId=" + materialId
                + ", claveMaterial=" + claveMaterial + ", nombreMaterial=" + nombreMaterial + ", usoMaterial="
                + usoMaterial + ", precioUnitario=" + precioUnitario + "]";
    }

    



}
