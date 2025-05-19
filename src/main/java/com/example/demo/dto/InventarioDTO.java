package com.example.demo.dto;

public class InventarioDTO {

    private Integer idMateriales;
    private String seccion;
    private Integer idSeccion;
    private String nombreMaterial;
    private String areaUbicacion;
    private String clave;
    private Integer cantidad;
    private String fechaRegistro;
    private float precioUnitario;
    private String usos;
    private Integer tipoMaterial;
    private String noSerie;

    public InventarioDTO(Integer idMateriales, String seccion, Integer idSeccion, String nombreMaterial,
            String areaUbicacion, String clave, Integer cantidad, String fechaRegistro, float precioUnitario,
            String usos, Integer tipoMaterial, String noSerie) {
        this.idMateriales = idMateriales;
        this.seccion = seccion;
        this.idSeccion = idSeccion;
        this.nombreMaterial = nombreMaterial;
        this.areaUbicacion = areaUbicacion;
        this.clave = clave;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
        this.precioUnitario = precioUnitario;
        this.usos = usos;
        this.tipoMaterial = tipoMaterial;
        this.noSerie = noSerie;
    }

    public InventarioDTO() {

    }

    public Integer getIdMateriales() {
        return idMateriales;
    }

    public void setIdMateriales(Integer idMateriales) {
        this.idMateriales = idMateriales;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getAreaUbicacion() {
        return areaUbicacion;
    }

    public void setAreaUbicacion(String areaUbicacion) {
        this.areaUbicacion = areaUbicacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getUsos() {
        return usos;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public Integer getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(Integer tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    @Override
    public String toString() {
        return "InventarioDTO [IdMateriales=" + idMateriales + ", seccion=" + seccion + ", idSeccion=" + idSeccion
                + ", nombreMaterial=" + nombreMaterial + ", areaUbicacion=" + areaUbicacion + ", clave=" + clave
                + ", cantidad=" + cantidad + ", fechaRegistro=" + fechaRegistro + ", precioUnitario=" + precioUnitario
                + ", usos=" + usos + ", tipoMaterial=" + tipoMaterial + ", noSerie=" + noSerie + ", getIdMateriales()="
                + getIdMateriales() + ", getIdSeccion()=" + getIdSeccion() + ", getClass()=" + getClass()
                + ", getSeccion()=" + getSeccion() + ", getNombreMaterial()=" + getNombreMaterial()
                + ", getAreaUbicacion()=" + getAreaUbicacion() + ", getClave()=" + getClave() + ", getCantidad()="
                + getCantidad() + ", getFechaRegistro()=" + getFechaRegistro() + ", getPrecioUnitario()="
                + getPrecioUnitario() + ", getUsos()=" + getUsos() + ", getTipoMaterial()=" + getTipoMaterial()
                + ", getNoSerie()=" + getNoSerie() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

}