package com.example.demo.dto;

public class CategoriaDTO {

    private Integer idCategoria;
    private String nombre;

    public CategoriaDTO() {

    }

    public CategoriaDTO(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaDTO [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
    }

}
