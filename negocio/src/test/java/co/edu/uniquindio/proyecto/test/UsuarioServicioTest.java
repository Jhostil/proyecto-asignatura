package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest (){
        Usuario usuario = new Usuario("123","Pepito", "pepe@email.com", "pepe123", "elpepe");
        try {
          Usuario respuesta =  usuarioServicio.registrarUsuario(usuario);
          Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest(){


        try {
                        usuarioServicio.eliminarUsuario("123");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

    }

    @Test
    public void listarTest()
    {
        List<Usuario> lista = usuarioServicio.listarUsuarios();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest ()
    {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("123");

            usuario.setPassword("elpepe123");

            Usuario respuesta =  usuarioServicio.actualizarUsuario(usuario);

            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest()
    {
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("pepe@email.com", "pepe123");

            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false,e.getMessage());
        }
    }



}

