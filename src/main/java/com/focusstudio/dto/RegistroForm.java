package com.focusstudio.dto;
import jakarta.validation.constraints.*;
public class RegistroForm {
 @NotBlank @Size(max=120) private String nombre;
 @NotBlank @Email @Size(max=180) private String correo;
 @NotBlank @Size(min=8,max=72) private String password;
 public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getCorreo(){return correo;} public void setCorreo(String v){correo=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
}
