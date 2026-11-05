package com.focusstudio.service;
import com.focusstudio.repository.*;
import org.springframework.stereotype.Service;
@Service public class ResumenService {
 private final UsuarioRepository usuarios;private final CategoriaRepository categorias;private final ReservaReferenciaRepository reservas;
 public ResumenService(UsuarioRepository u,CategoriaRepository c,ReservaReferenciaRepository r){usuarios=u;categorias=c;reservas=r;}
 public long usuarios(){return usuarios.count();} public long activos(){return usuarios.countByActivoTrue();}
 public long categorias(){return categorias.countByActivaTrue();} public long reservas(){return reservas.count();}
 public long pendientes(){return reservas.countByEstado("PENDIENTE");} public long confirmadas(){return reservas.countByEstado("CONFIRMADA");}
 public long completadas(){return reservas.countByEstado("COMPLETADA");} public long canceladas(){return reservas.countByEstado("CANCELADA");}
}
