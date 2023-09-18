package com.example.semana7.domain.ports.out;

import com.example.semana7.domain.model.Persona;
import com.example.semana7.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> update(Long id, Usuario usuario);
    boolean deleteById(Long id);
}
