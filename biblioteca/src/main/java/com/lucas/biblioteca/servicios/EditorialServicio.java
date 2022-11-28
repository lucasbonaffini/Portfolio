package com.lucas.biblioteca.servicios;

import com.lucas.biblioteca.entidades.Autor;
import com.lucas.biblioteca.entidades.Editorial;
import com.lucas.biblioteca.entidades.Libro;
import com.lucas.biblioteca.exceptions.MiException;
import com.lucas.biblioteca.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {
    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        if(nombre == null){
            throw new MiException("Name can't be null");
        }

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }
    public List<Editorial> listarEditoriales () {

        List <Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }
    public void modificarEditorial(String id, String nombre) throws MiException {

        if(id.isEmpty()){
            throw new MiException("Id can't be empty");
        }
        if(nombre == null){
            throw new MiException("Name can't be null");
        }

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()){
            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);

        }
    }
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
}
