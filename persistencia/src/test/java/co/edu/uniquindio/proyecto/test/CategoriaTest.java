package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


//Clase para realizar la prueba unitaria del CRUD de la entidad Categoría
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    //Repositorio de la entidad categoría

    @Autowired
    private CategoriaRepo categoriaRepo;

    //Método test para comprobar la correcta ejecución a la hora de crear una categoría
    @Test
    public void crearTest(){

        Categoria categoria = new Categoria("Hogar");

        Categoria categoria1 = categoriaRepo.save(categoria);

        Assertions.assertNotNull(categoria1);
    }

    //Método test para verificar que se elimine correctamente una categoría de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Categoria categoria = categoriaRepo.findById(1).orElse(null);

        categoriaRepo.delete(categoria);

        Categoria categoria1 = categoriaRepo.findById(1).orElse(null);

        Assertions.assertNull(categoria1);
    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de una categoría
    @Test
    @Sql("classpath:datos.sql")
    public void modificarTest(){

        Categoria categoria = categoriaRepo.findById(1).orElse(null);

        categoria.setNombre("Oficina");

        categoriaRepo.save(categoria);

        Categoria categoria1 = categoriaRepo.findById(1).orElse(null);

        Assertions.assertEquals("Oficina", categoria1.getNombre());
    }

    //Método test para listar las categorías que se encuentran guardadas en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Categoria> categoriaList = categoriaRepo.findAll();

        categoriaList.forEach(categoria -> System.out.println(categoria));
    }



}
