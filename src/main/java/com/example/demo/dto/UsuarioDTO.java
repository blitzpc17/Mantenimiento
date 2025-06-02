package com.example.demo.dto;

public class UsuarioDTO {

    private Integer usuarioId;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String tipoId;
    private String usuario;
    private String contrasena;
    private Integer personaId;

    public UsuarioDTO(Integer usuarioId, String nombre, String apellido, String correoElectronico, String tipoId,
            String usuario, String contrasena, Integer personaId) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.tipoId = tipoId;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.personaId = personaId;
    }

    public UsuarioDTO() {
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    @Override
    public String toString() {
        return "UsuarioDTO [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellido=" + apellido
                + ", correoElectronico=" + correoElectronico + ", tipoId=" + tipoId + ", usuario=" + usuario
                + ", contrasena=" + contrasena + ", personaId=" + personaId + "]";
    }

}
