package com.focusstudio.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
// Modelo provisional: sustituir por la entidad Reservacion acordada por el equipo al integrar HU-05/HU-11.
@Entity @Table(name="fs_reserva_referencia") public class ReservaReferencia {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String codigo;
 @Column(nullable=false) private String servicio;
 @Column(nullable=false) private String cliente;
 @Column(nullable=false) private LocalDateTime fechaHora;
 @Column(nullable=false) private String ubicacion;
 @Column(nullable=false) private String estado="PENDIENTE";
 public Long getId(){return id;} public String getCodigo(){return codigo;} public void setCodigo(String v){codigo=v;}
 public String getServicio(){return servicio;} public void setServicio(String v){servicio=v;}
 public String getCliente(){return cliente;} public void setCliente(String v){cliente=v;}
 public LocalDateTime getFechaHora(){return fechaHora;} public void setFechaHora(LocalDateTime v){fechaHora=v;}
 public String getUbicacion(){return ubicacion;} public void setUbicacion(String v){ubicacion=v;}
 public String getEstado(){return estado;} public void setEstado(String v){estado=v;}
}
