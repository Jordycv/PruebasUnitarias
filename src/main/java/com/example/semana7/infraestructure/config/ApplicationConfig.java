package com.example.semana7.infraestructure.config;

import com.example.semana7.application.service.PersonaService;
import com.example.semana7.application.service.UsuarioService;
import com.example.semana7.application.usecase.PersonaUseCaseImpl;
import com.example.semana7.application.usecase.UsuarioUseCaseImpl;
import com.example.semana7.domain.ports.out.PersonaRepositoryPort;
import com.example.semana7.domain.ports.out.UsuarioRepositoryPort;
import com.example.semana7.infraestructure.repository.PersonaJpaRepositoryAdapter;
import com.example.semana7.infraestructure.repository.UsuarioJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PersonaService personaService(PersonaRepositoryPort personaRepositoryPort){
        return new PersonaService(new PersonaUseCaseImpl(personaRepositoryPort));
    }

    @Bean
    public PersonaRepositoryPort personaRepositoryPort(PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter){
        return personaJpaRepositoryAdapter;
    }

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioService(new UsuarioUseCaseImpl(usuarioRepositoryPort));
    }


    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter){
        return usuarioJpaRepositoryAdapter;
    }



}
