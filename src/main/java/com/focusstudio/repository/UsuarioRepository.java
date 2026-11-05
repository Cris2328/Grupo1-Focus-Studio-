package com.focusstudio.repository;
import com.focusstudio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario,Long> { java.util.Optional<Usuario> findByCorreoIgnoreCase(String correo); boolean existsByCorreoIgnoreCase(String correo); long countByActivoTrue();}
