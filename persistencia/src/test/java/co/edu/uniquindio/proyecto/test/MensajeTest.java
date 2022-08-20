package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

//Pruebas unitarias Mensaje
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    //Test registrar Mensaje
    @Test
    @Sql("classpath:datos.sql")
    public void registrarTest(){
        //Chat chatGuardado = chatRepo.getById(1);

        Chat chatGuardado = chatRepo.getOne(1);

        Mensaje mensajeNuevo = new Mensaje("Este es un mensaje","Juan Pablo", LocalDateTime.now(),chatGuardado);

        Mensaje mensajeGuardado = mensajeRepo.save(mensajeNuevo);

        Assertions.assertNotNull(mensajeGuardado);
    }

    //Test eliminar Mensaje
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Mensaje guardado = mensajeRepo.findById(2).orElse(null);

        Assertions.assertNotNull(guardado);

        mensajeRepo.delete(guardado);

        Mensaje guardado2 = mensajeRepo.findById(2).orElse(null);

        Assertions.assertNull(guardado2);
    }

    //Test actualizar Mensaje
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest(){

        Mensaje guardado = mensajeRepo.findById(3).orElse(null);
        guardado.setMensaje("Cambiamos mensaje");

        mensajeRepo.save(guardado);

        Mensaje mensajeBuscado = mensajeRepo.findById(3).orElse(null);

        Assertions.assertEquals("Cambiamos mensaje", mensajeBuscado.getMensaje());
    }

    //Test listar Mensaje
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Mensaje> mensajes = mensajeRepo.findAll();

        mensajes.forEach(u -> System.out.println(u));
    }
}
