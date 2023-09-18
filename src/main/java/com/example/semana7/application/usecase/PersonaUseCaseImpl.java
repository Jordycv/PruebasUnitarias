package com.example.semana7.application.usecase;

import com.example.semana7.domain.model.Persona;
import com.example.semana7.domain.ports.in.PersonaUseCase;
import com.example.semana7.domain.ports.out.PersonaRepositoryPort;

import java.util.Optional;

public class PersonaUseCaseImpl implements PersonaUseCase {

    private final PersonaRepositoryPort personaRepositoryPort;

    public PersonaUseCaseImpl(PersonaRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepositoryPort.save(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaRepositoryPort.findById(id);
    }

    @Override
    public Optional<Persona> updatePersona(Long id, Persona persona) {
        return personaRepositoryPort.update(id,persona);
    }

    @Override
    public boolean deletePersona(Long id) {
        return personaRepositoryPort.deleteById(id);
    }
}
