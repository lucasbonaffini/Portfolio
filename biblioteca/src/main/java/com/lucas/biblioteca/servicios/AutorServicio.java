package com.lucas.biblioteca.servicios;

import com.lucas.biblioteca.entidades.Autor;
import com.lucas.biblioteca.entidades.Libro;
import com.lucas.biblioteca.exceptions.MiException;
import com.lucas.biblioteca.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        if(nombre == null || nombre.isEmpty()){
            throw new MiException("Name can't be null or empty");
        }

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }
    public List<Autor> listarAutores () {

        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }
    @Transactional
    public void modificarAutor (String nombre,String id) throws MiException {

        if(id.isEmpty()){
            throw new MiException("Id can't be empty");
        }
        if(nombre == null || nombre.isEmpty()){
            throw new MiException("Name can't be null or empty");
        }

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
}
