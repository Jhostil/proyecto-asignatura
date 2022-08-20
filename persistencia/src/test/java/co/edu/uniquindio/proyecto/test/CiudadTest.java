package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase para realizar la prueba unitaria del CRUD de la entidad Ciudad
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {


    //Repositorio de la entidad ciudad
    @Autowired
    private CiudadRepo ciudadRepo;

    //Método test para comprobar la correcta ejecución a la hora de crear una ciudad
    @Test
    public void crearCiudadTest(){
        Ciudad ciudad = new Ciudad("Montenegro");
        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudadGuardada);
    }

    //Método test para verificar que se elimine correctamente una ciudad de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarCiudadTest(){

        Ciudad guardada = ciudadRepo.findById(1).orElse(null);

        Assertions.assertNotNull(guardada);

        ciudadRepo.delete(guardada);

        Ciudad guardada2 = ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(guardada2);
    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de una ciudad
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarCiudadTest(){

        Ciudad guardada = ciudadRepo.findById(1).orElse(null);
        guardada.setNombre("Tebaida");

        ciudadRepo.save(guardada);

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Assertions.assertEquals("Tebaida", ciudadBuscada.getNombre());
    }

    //Método test para listar las ciudades que se encuentran guardadas en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarCiudadesTest(){

        List<Ciudad> ciudades = ciudadRepo.findAll();

        ciudades.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuariosCiudadTest(){

        List<Usuario> usuarios = ciudadRepo.listarUsuario("Calarcá");

        Assertions.assertEquals(2,usuarios.size());
    }
}
