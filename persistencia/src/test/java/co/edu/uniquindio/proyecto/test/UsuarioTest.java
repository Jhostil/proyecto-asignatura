package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Pruebas unitaria Usuario
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //Test registrar Usuario
    @Test
    @Sql("classpath:datos.sql")
    public void registrarTest(){
        Usuario usuarioNuevo = new Usuario("126","Lee Lopez","elchino@email.com","micontlasena", "lee");

        Usuario usuarioGuardado = usuarioRepo.save(usuarioNuevo);

        Assertions.assertNotNull(usuarioGuardado);
    }

    //Test eliminar Usuario
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Usuario guardado = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNotNull(guardado);

        usuarioRepo.delete(guardado);

        Usuario guardado2 = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNull(guardado2);
    }

    //Test auctualizar Usuario
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest(){

        Usuario guardado = usuarioRepo.findById("124").orElse(null);
        guardado.setEmail("maria1@email.com");

        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("124").orElse(null);

        Assertions.assertEquals("maria1@email.com", usuarioBuscado.getEmail());
    }

    //Test listar Usuario
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();

        usuarios.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void filtrarNombreTest(){

        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Pepe");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void filtrarEmailTest(){

        Optional<Usuario> usuario = usuarioRepo.findByEmail("juan@email.com");

        if(usuario.isPresent()){
            System.out.println(usuario.get());
        }else{
            System.out.println("no existe ese correo");
        }
    }

    @Test
    @Sql("classpath:datos.sql")
    public void paginarListaTest(){

        Pageable paginador = PageRequest.of(0,2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void ordenarListaTest(){

        Pageable paginador = PageRequest.of(0,2);
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));

        System.out.println(lista);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerFavoritosTest(){

        List<Producto> favoritos = usuarioRepo.obtenerProductosFavoritos("juan@email.com");
        favoritos.forEach(System.out::println);
        Assertions.assertEquals(2,favoritos.size());
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuariosProductosTest(){

        List<UsuarioYProducto> respuesta = usuarioRepo.listarUsuariosYProductos();

        respuesta.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuariosComentariosTest(){

        List<Usuario> usuarios= usuarioRepo.listarUsuariosComentario(1);
        usuarios.forEach(System.out::println);
    }
}
