package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase para realizar la prueba unitaria del CRUD de la entidad Administrador

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    //Repositorio de la entidad admnistrador
    @Autowired
    private AdministradorRepo administradorRepo;

    //Método test para comprobar la correcta ejecución a la hora de crear un administrador
    @Test
    public void crearTest (){

        Administrador administrador = new Administrador("12345", "Santiago", "santiago@email.com", "santi123");

        Administrador adminGuardado = administradorRepo.save(administrador);

        Assertions.assertNotNull(adminGuardado);

    }

    //Método test para verificar que se elimine correctamente un admnistrador de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest (){

        Administrador administrador = administradorRepo.findById("122").orElse(null);
        administradorRepo.delete(administrador);

        Administrador administrador1 = administradorRepo.findById("122").orElse(null);

        Assertions.assertNull(administrador1);
    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de un administrador
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest (){

        Administrador administrador = administradorRepo.findById("122").orElse(null);
        administrador.setNombre("William");

        administradorRepo.save(administrador);

        Administrador administrador1 = administradorRepo.findById("122").orElse(null);

        Assertions.assertEquals("William", administrador1.getNombre());
    }

    //Método test para listar los administradores que se encuentran guardados en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest (){

        List<Administrador> administradorList = administradorRepo.findAll();

        administradorList.forEach(administrador -> System.out.println(administrador));

    }

}
