package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {


    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id) throws Exception;

    Categoria obtenerCatPorNombre(String nombre) throws Exception;

    boolean existenCat();

    Categoria registrarCategoria (Categoria categoria) throws Exception;

}
