package com.focusstudio.domain;
import jakarta.persistence.*;
@Entity @Table(name="fs_categoria") public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nombre;
 @Column(nullable=false,length=600) private String descripcion;
 private boolean activa=true;
 public Long getId(){return id;} public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
 public boolean isActiva(){return activa;} public void setActiva(boolean v){activa=v;}
}
