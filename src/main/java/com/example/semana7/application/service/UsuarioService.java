package com.example.semana7.application.service;

import com.example.semana7.domain.model.Persona;
import com.example.semana7.domain.model.Usuario;
import com.example.semana7.domain.ports.in.PersonaUseCase;
import com.example.semana7.domain.ports.in.UsuarioUseCase;
import com.example.semana7.infraestructure.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

public class UsuarioService implements UsuarioUseCase {


    private final UsuarioUseCase usuarioUseCase;

    @Autowired
    private PersonaUseCase personaUseCase;

    public UsuarioService(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }


    @Override
    public Usuario crearUsuario(Usuario usuario) {
        Persona persona=personaUseCase.getPersona(usuario.getPersonaId()).get();
        PersonaEntity personaEntity=new PersonaEntity();
        personaEntity.setId(usuario.getPersonaId());
        personaEntity.setNombre(persona.getNombre());
        personaEntity.setApellido(persona.getApellido());
        personaEntity.setFechaNacimiento(persona.getFechaNacimiento());
        personaEntity.setGenero(persona.getGenero());

        usuario.setPersonaEntity(personaEntity);
        return usuarioUseCase.crearUsuario(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {

        return usuarioUseCase.getUsuario(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        return usuarioUseCase.updateUsuario(id,usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioUseCase.deleteUsuario(id);
    }
}
