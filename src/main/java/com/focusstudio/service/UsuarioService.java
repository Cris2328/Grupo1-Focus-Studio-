package com.focusstudio.service;
import com.focusstudio.domain.Usuario;
import com.focusstudio.dto.RegistroForm;
import com.focusstudio.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service public class UsuarioService {
 private final UsuarioRepository repo; private final PasswordEncoder encoder;
 public UsuarioService(UsuarioRepository repo,PasswordEncoder encoder){this.repo=repo;this.encoder=encoder;}
 @Transactional public void registrar(RegistroForm f){String correo=f.getCorreo().trim().toLowerCase();if(repo.existsByCorreoIgnoreCase(correo))throw new IllegalArgumentException("Este correo ya está registrado");Usuario u=new Usuario();u.setNombre(f.getNombre().trim());u.setCorreo(correo);u.setPasswordHash(encoder.encode(f.getPassword()));repo.save(u);}
 public List<Usuario> todos(){return repo.findAll();}
 @Transactional public void cambiarEstado(Long id,boolean activo){Usuario u=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Usuario no encontrado"));u.setActivo(activo);}
}
