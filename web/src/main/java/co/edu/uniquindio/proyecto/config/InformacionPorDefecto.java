package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Override
    public void run(String... args) throws Exception {

        if(!categoriaServicio.existenCat()){
            Categoria cat = new Categoria("Tecnología");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Hogar");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Limpieza");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Electrodomésticos");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Alimentos");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Animales y Mascotas");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Ciudado personal");
            categoriaServicio.registrarCategoria(cat);

            cat = new Categoria("Ropa");
            categoriaServicio.registrarCategoria(cat);
        }
    }
}
