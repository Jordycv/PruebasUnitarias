package com.example.semana7.domain.model;


import com.example.semana7.infraestructure.entity.PersonaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombreUsuario;
    private String contrasena;
    private String correoElectronico;
    private Long personaId;

    @JsonIgnore
    private PersonaEntity personaEntity;


    public Usuario(Long id, String nombreUsuario, String contrasena, String correoElectronico, Long personaId) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.personaId = personaId;
    }
}
