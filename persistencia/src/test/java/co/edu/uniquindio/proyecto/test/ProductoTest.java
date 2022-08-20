package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//Pruebas unitarias Producto
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    //Test registrar Producto
    @Test
    @Sql("classpath:datos.sql")
    public void registrarTest(){
        Producto productoNuevo = new Producto("Steam desk","SteamDesk", 23,"Consola de videojuegos portatil",3500000,0.5, LocalDate.now(), null);

        Producto productoGuardado = productoRepo.save(productoNuevo);

        Assertions.assertNotNull(productoGuardado);
    }

    //Test eliminar Producto
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Producto guardado = productoRepo.findById(1).orElse(null);

        Assertions.assertNotNull(guardado);

        productoRepo.delete(guardado);

        Producto guardado2 = productoRepo.findById(1).orElse(null);

        Assertions.assertNull(guardado2);
    }

    //Test actualizar Producto
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest(){

        Producto guardado = productoRepo.findById(2).orElse(null);
        guardado.setPrecio(60000);

        productoRepo.save(guardado);

        Producto usuarioBuscado = productoRepo.findById(2).orElse(null);

        Assertions.assertEquals(60000, usuarioBuscado.getPrecio());
    }

    //Test listar Producto
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Producto> producto = productoRepo.findAll();

        producto.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerNombreVendedorTest(){

        String nombre = productoRepo.obtenerNombreVendedor(2);
        Assertions.assertEquals("Joan Perez", nombre);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarProductosYComentariosTest(){

        List<Object[]> respuesta = productoRepo.listarProductosComentarios();

        respuesta.forEach(objeto -> System.out.println(objeto[0]+"----"+objeto[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarProductosValidostest(){

        List<Object[]> productos = productoRepo.listarProductosValidos(LocalDateTime.now());

        productos.forEach(p -> System.out.println(p[0]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarProductosCategoriasTest(){

        List<Object[]> productos = productoRepo.obtenerTotalProductosPorCategoria();
        productos.forEach(p -> System.out.println(p[0]+", "+p[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarProductosSinComentariosTest(){

        List<Producto> productos = productoRepo.obtenerProductosSinComentarios();
        productos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void buscarProductosPorNombreTest(){

        List<Producto> productos = productoRepo.buscarProductosPorNombre("Iphone");
        productos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerProductosEnVentaTest(){

        List<ProductosPorUsuario> productos = productoRepo.obtenerProductosEnVenta();
        productos.forEach(System.out::println);
    }

}
