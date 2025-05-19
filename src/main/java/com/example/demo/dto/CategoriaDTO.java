package com.example.demo.dto;

public class CategoriaDTO {

    private Integer IdCategoria;
    private String Nombre;

    public CategoriaDTO() {

    }

    public CategoriaDTO(Integer idCategoria, String nombre) {
        IdCategoria = idCategoria;
        Nombre = nombre;
    }

    public Integer getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
