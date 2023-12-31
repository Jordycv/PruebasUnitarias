package com.example.semana7.infraestructure.repository;

import com.example.semana7.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaJpaRepository  extends JpaRepository<PersonaEntity,Long> {
}
