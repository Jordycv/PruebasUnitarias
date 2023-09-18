package com.example.semana7.domain.ports.in;

import com.example.semana7.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> getUsuario(Long id);

    Optional<Usuario> updateUsuario(Long id, Usuario usuario);
    boolean deleteUsuario(Long id);
}
