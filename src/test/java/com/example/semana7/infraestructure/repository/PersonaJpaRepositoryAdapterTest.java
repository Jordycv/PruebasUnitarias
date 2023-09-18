package com.example.semana7.infraestructure.repository;

import com.example.semana7.domain.model.Persona;
import com.example.semana7.infraestructure.entity.PersonaEntity;
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

class PersonaJpaRepositoryAdapterTest {
    @Mock
    PersonaJpaRepository personaJpaRepository;

    @InjectMocks
    PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaJpaRepositoryAdapter = new PersonaJpaRepositoryAdapter(personaJpaRepository);
    }

    @Test
    void saveExitosoPersonaEntity(){
        //Loque enviamos al metodo de la clase qeu estamos probando
        Persona persona = new Persona(1L,"Paul","Rodriguez", new Date(),"Masculino");

        //Lo que enviamos a BD como simulación
        PersonaEntity personaEntity = new PersonaEntity(1L,"Paul","Rodriguez", new Date(),"Masculino");

        //Simulando Comportamiento
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);

        Persona personaAdapter = personaJpaRepositoryAdapter.save(persona);

        assertNotNull(personaAdapter);
        assertEquals(persona.getId(),personaAdapter.getId());
        assertEquals(persona.getNombre(),personaAdapter.getNombre());
    }

    @Test
    void findByExitoso(){
    Long id = 1L;
    PersonaEntity personaEntity = new PersonaEntity(1L,"Paul","Rodriguez", new Date(),"Masculino");
    when(personaJpaRepository.findById(id)).thenReturn(Optional.of(personaEntity));
    Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.findById(id);
    personaAdapter.map(persona -> {
        Long personaId = persona.getId();
        assertEquals(personaId,id);
        return personaId;
    });
    assertNotNull(personaAdapter);

    }

    @Test
    void findById_IsEmpty(){
        Long id = 1L;

        when(personaJpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Persona> optionalPersona = personaJpaRepositoryAdapter.findById(id);

        assertTrue(optionalPersona.isEmpty());
    }

    @Test
    void updateTestExitoso(){
        Long id = 1L;
        Persona personaActualizada = new Persona(id,"Jordy","Caichihua",new Date(),"Masculino");

        when(personaJpaRepository.existsById(id)).thenReturn(true);

        PersonaEntity personaEntityActualizada = PersonaEntity.fromDomainModel(personaActualizada);
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntityActualizada);

        Optional<Persona> resultado = personaJpaRepositoryAdapter.update(id, personaActualizada);

        assertTrue(resultado.isPresent());

        Persona personaActualizadaResultado = resultado.get();
        assertEquals(personaEntityActualizada.getId(), personaActualizadaResultado.getId());
        assertEquals(personaActualizada.getNombre(), personaActualizadaResultado.getNombre());
        assertEquals(personaActualizada.getApellido(), personaActualizadaResultado.getApellido());
        assertEquals(personaActualizada.getGenero(), personaActualizadaResultado.getGenero());
    }

    @Test
    void testUpdateCuandoNoExistePersona2(){
        Long id = 1L;
        Persona personaActualizada = new Persona(id,"Jordy","Caichihua",new Date(),"Masculino");
        when(personaJpaRepository.existsById(id)).thenReturn(false);

        Optional<Persona> optionalPersona = personaJpaRepositoryAdapter.update(id,personaActualizada);

        assertTrue(optionalPersona.isEmpty());
    }

}
