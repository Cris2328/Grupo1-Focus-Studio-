package com.focusstudio.service;
import com.focusstudio.domain.Categoria;
import com.focusstudio.dto.CategoriaForm;
import com.focusstudio.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service public class CategoriaService {
 private final CategoriaRepository repo;
 public CategoriaService(CategoriaRepository repo){this.repo=repo;}
 public List<Categoria> todas(){return repo.findAll();}
 public Categoria buscar(Long id){return repo.findById(id).orElseThrow(()->new IllegalArgumentException("Categoría no encontrada"));}
 @Transactional public void guardar(Long id,CategoriaForm form){Categoria c=id==null?new Categoria():buscar(id);c.setNombre(form.getNombre().trim());c.setDescripcion(form.getDescripcion().trim());repo.save(c);}
 @Transactional public void cambiarEstado(Long id,boolean activa){buscar(id).setActiva(activa);}
}
