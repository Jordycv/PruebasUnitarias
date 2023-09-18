package com.example.semana7.infraestructure.repository;

import com.example.semana7.domain.model.Persona;
import com.example.semana7.domain.model.Usuario;
import com.example.semana7.infraestructure.entity.PersonaEntity;
import com.example.semana7.infraestructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioJpaRepositoryAdapterTest {

    @Mock
    UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioJpaRepositoryAdapter = new UsuarioJpaRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    void saveExitosoUsuarioEntity(){
        //Loque enviamos al metodo de la clase qeu estamos probando
        Usuario usuario = new Usuario(1L,"Goku","123456","goku@tierra.com" ,1L);

        PersonaEntity personaEntity = new PersonaEntity(1L,"Paul","Rodriguez", new Date(),"Masculino");
        //Lo que enviamos a BD como simulación
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L,"Goku","123456", "goku@tierra.com",personaEntity);

        //Simulando Comportamiento
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario usuarioAdapter = usuarioJpaRepositoryAdapter.save(usuario);

        assertNotNull(usuarioAdapter);
        assertEquals(usuario.getId(),usuarioAdapter.getId());
        assertEquals(usuario.getNombreUsuario(),usuarioAdapter.getNombreUsuario());
    }

    @Test
    void findByExitoso(){
        Long id = 1L;
        PersonaEntity personaEntity = new PersonaEntity(1L,"Paul","Rodriguez", new Date(),"Masculino");
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L,"Goku","123456", "goku@tierra.com",personaEntity);
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.findById(id);
        usuarioAdapter.map(usuario -> {
            Long usuarioId = usuario.getId();
            assertEquals(usuarioId,id);
            return usuarioId;
        });
        assertNotNull(usuarioAdapter);

    }

    @Test
    void findById_IsEmpty(){
        Long id = 1L;

        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Usuario> optionalUsuario = usuarioJpaRepositoryAdapter.findById(id);

        assertTrue(optionalUsuario.isEmpty());
    }


    @Test
    void updateTestExitoso(){
        Long id = 1L;
        Usuario usuarioActualizada = new Usuario(id,"Goku","123456","goku@tierra.com" ,1L);

        when(usuarioJpaRepository.existsById(id)).thenReturn(true);


        PersonaEntity personaEntity = new PersonaEntity(1L,"Paul","Rodriguez", new Date(),"Masculino");
        //Lo que enviamos a BD como simulación
        UsuarioEntity usuarioEntityActualizada = new UsuarioEntity(1L,"Goku","123456", "goku@tierra.com",personaEntity);

        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntityActualizada);

        Optional<Usuario> resultado = usuarioJpaRepositoryAdapter.update(id, usuarioActualizada);

        assertTrue(resultado.isPresent());

        Usuario usuarioActualizadaResultado = resultado.get();
        assertEquals(usuarioEntityActualizada.getId(), usuarioActualizadaResultado.getId());
        assertEquals(usuarioActualizada.getNombreUsuario(), usuarioActualizadaResultado.getNombreUsuario());
        assertEquals(usuarioActualizada.getContrasena(), usuarioActualizadaResultado.getContrasena());
        assertEquals(usuarioActualizada.getCorreoElectronico(), usuarioActualizadaResultado.getCorreoElectronico());
        assertEquals(usuarioActualizada.getPersonaId(), usuarioActualizadaResultado.getPersonaId());
    }

    @Test
    void testUpdateCuandoNoExisteUsuario(){
        Long id = 1L;
        Usuario usuarioActualizada = new Usuario(id,"Goku","123456","goku@tierra.com" ,1L);

        when(usuarioJpaRepository.existsById(id)).thenReturn(false);

        Optional<Usuario> optionalPersona = usuarioJpaRepositoryAdapter.update(id,usuarioActualizada);

        assertTrue(optionalPersona.isEmpty());
    }
}