package com.example.semana7.domain.ports.in;

import com.example.semana7.domain.model.Persona;

import java.util.Optional;

public interface PersonaUseCase {
    Persona crearPersona(Persona persona);

    Optional<Persona> getPersona(Long id);

    Optional<Persona> updatePersona(Long id, Persona persona);
    boolean deletePersona(Long id);
}
