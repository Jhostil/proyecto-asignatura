package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase para realizar la prueba unitaria del CRUD de la entidad Comentario
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    //Repositorio de la entidad comentario
    @Autowired
    private ComentarioRepo comentarioRepo;

    //Repositorio de la entidad producto
    @Autowired
    private ProductoRepo productoRepo;

    //Repositorio de la entidad usuario
    @Autowired
    private UsuarioRepo usuarioRepo;

    //Método test para comprobar la correcta ejecución a la hora de crear un comentario
    @Test
    @Sql("classpath:datos.sql")
    public void agregarTest(){
        //Usuario usuario = usuarioRepo.getById("123");
        Usuario usuario = usuarioRepo.getOne("123");
        Producto producto = productoRepo.findById(1).orElse(null);

        Comentario comentario = new Comentario("¿Cuánta garantía tiene el producto?", 4, usuario, producto);

        Comentario comentario1 = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentario1);

    }

    //Método test para verificar que se elimine correctamente un comentario de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Comentario comentario = comentarioRepo.findById(1).orElse(null);

        comentarioRepo.delete(comentario);

        Comentario comentario1 = comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(comentario1);


    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de un comentario
    @Test
    @Sql("classpath:datos.sql")
    public void modificarTest(){

        Comentario comentario = comentarioRepo.findById(1).orElse(null);

        comentario.setCalificacion(4);

        Comentario comentario1 = comentarioRepo.save(comentario);

        Assertions.assertEquals(4,comentario1.getCalificacion());

    }

    //Método test para listar los comentarios que se encuentran guardados en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Comentario> comentarioList = comentarioRepo.findAll();

        comentarioList.forEach(comentario -> System.out.println(comentario));

    }

}
