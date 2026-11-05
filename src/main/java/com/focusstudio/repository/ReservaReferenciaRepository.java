package com.focusstudio.repository;
import com.focusstudio.domain.ReservaReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservaReferenciaRepository extends JpaRepository<ReservaReferencia,Long> { java.util.Optional<ReservaReferencia> findByCodigo(String codigo); long countByEstado(String estado);}
