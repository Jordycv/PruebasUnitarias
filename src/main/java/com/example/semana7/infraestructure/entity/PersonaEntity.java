package com.example.semana7.infraestructure.entity;

import com.example.semana7.domain.model.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name="personas")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public PersonaEntity(){

    }

    public PersonaEntity(Long id, String nombre, String apellido, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public static PersonaEntity fromDomainModel(Persona persona){
        return new PersonaEntity(persona.getId(),persona.getNombre(), persona.getApellido(), persona.getFechaNacimiento(), persona.getGenero());
    }
    public Persona toDomainModel(){
        return new Persona(id,nombre,apellido,fechaNacimiento,genero);
    }

}
