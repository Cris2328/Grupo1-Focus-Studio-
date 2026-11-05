package com.focusstudio.domain;
import jakarta.persistence.*;
@Entity @Table(name="fs_usuario",uniqueConstraints=@UniqueConstraint(columnNames="correo"))
public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String nombre;
 @Column(nullable=false,length=180) private String correo;
 @Column(nullable=false) private String passwordHash;
 @Column(nullable=false) private boolean activo=true;
 public Long getId(){return id;} public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getCorreo(){return correo;} public void setCorreo(String v){correo=v;}
 public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
 public boolean isActivo(){return activo;} public void setActivo(boolean v){activo=v;}
}
