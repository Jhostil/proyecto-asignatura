package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Clase para realizar la prueba unitaria del CRUD de la entidad Chat
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    //Repositorio de la entidad chat
    @Autowired
    private ChatRepo chatRepo;

    //Repositorio de la entidad producto
    @Autowired
    private ProductoRepo productoRepo;

    //Repositorio de la entidad usuario
    @Autowired
    private UsuarioRepo usuarioRepo;

    //Repositorio de la entidad mensaje
    @Autowired
    private MensajeRepo mensajeRepo;

    //Método test para comprobar la correcta ejecución a la hora de crear un chat
    @Test
    @Sql("classpath:datos.sql")
    public void agregarTest(){

        //Usuario usuario = usuarioRepo.getById("123");
        Usuario usuario = usuarioRepo.getOne("123");
        Producto producto = productoRepo.findById(1).orElse(null);
        Mensaje mensaje = mensajeRepo.findById(1).orElse(null);

        List<Mensaje> mensajes = new ArrayList<>();
        mensajes.add(mensaje);

        Chat chat = new Chat(usuario,producto,mensajes);

        Chat chat1 = chatRepo.save(chat);

        Assertions.assertNotNull(chat1);

    }

    //Método test para verificar que se elimine correctamente un chat de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Chat chat = chatRepo.findById(1).orElse(null);

        chatRepo.delete(chat);

        Chat chat1 = chatRepo.findById(1).orElse(null);

        Assertions.assertNull(chat1);


    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de un chat
    @Test
    @Sql("classpath:datos.sql")
    public void modificarTest() {


        //Chat chat = chatRepo.getById(1);
        Chat chat = chatRepo.getOne(1);

        Producto producto = productoRepo.findById(2).orElse(null);

        chat.setProducto(producto);

        Chat chat1 = chatRepo.save(chat);

        Assertions.assertEquals(2, chat1.getProducto().getCodigo());

    }

    //Método test para listar los chats que se encuentran guardados en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Chat> chats = chatRepo.findAll();

        chats.forEach(chat -> System.out.println(chat));

    }

}
