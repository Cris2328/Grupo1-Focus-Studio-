package com.focusstudio.repository;
import com.focusstudio.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria,Long> { long countByActivaTrue();}
