package com.example.semana7.infraestructure.entity;


import com.example.semana7.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String contrasena;

    @Column(name = "correo_electronico")
    private String correoElectronico;
    @OneToOne
    @JoinColumn(name = "persona_id",referencedColumnName = "id")
    private PersonaEntity personaEntity;

    public UsuarioEntity(){
    }

    public UsuarioEntity(Long id, String nombreUsuario, String contrasena, String correoElectronico, PersonaEntity personaEntity) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.personaEntity=personaEntity;
    }

    public static UsuarioEntity fromDomainModel(Usuario usuario){
        return new UsuarioEntity(usuario.getId(),usuario.getNombreUsuario(), usuario.getContrasena(),
                usuario.getCorreoElectronico(),usuario.getPersonaEntity());
    }
    public Usuario toDomainModel(){
        return new Usuario(id,nombreUsuario,contrasena,correoElectronico,personaEntity.getId());
    }
}
