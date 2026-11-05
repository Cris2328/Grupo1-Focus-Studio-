package com.focusstudio.dto;
import jakarta.validation.constraints.*;
public class CategoriaForm {
 @NotBlank @Size(max=100) private String nombre;
 @NotBlank @Size(max=600) private String descripcion;
 public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
}
