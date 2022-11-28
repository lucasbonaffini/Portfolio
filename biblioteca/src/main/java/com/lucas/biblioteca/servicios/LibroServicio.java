package com.lucas.biblioteca.servicios;

import com.lucas.biblioteca.entidades.Autor;
import com.lucas.biblioteca.entidades.Editorial;
import com.lucas.biblioteca.entidades.Libro;
import com.lucas.biblioteca.exceptions.MiException;
import com.lucas.biblioteca.repositorios.AutorRepositorio;
import com.lucas.biblioteca.repositorios.EditorialRepositorio;
import com.lucas.biblioteca.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLIbro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validate(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }
    public List <Libro> listarLibros () {

        List <Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validate(isbn, titulo, ejemplares, idAutor, idEditorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()){
            autor = respuestaAutor.get();
        }
        if (respuestaEditorial.isPresent()){
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);

        }

    }
    private void validate(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        if(isbn == null){
            throw new MiException("Isbn can't be null");
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MiException("Title can't be null or empty");
        }
        if(ejemplares == null){
            throw new MiException("Ejemplares can't be null");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiException("Autor can't be null or empty");
        }
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiException("Editorial can't be null or empty");
        }
    }
}
