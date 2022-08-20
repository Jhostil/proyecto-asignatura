package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

//Clase para realizar la prueba unitaria del CRUD de la entidad Compra
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    //Repositorio de la entidad compra
    @Autowired
    private CompraRepo compraRepo;

    //Repositorio de la entidad usuario
    @Autowired
    private UsuarioRepo usuarioRepo;

    //Repositorio de la entidad detalleCompra
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    //Método test para comprobar la correcta ejecución a la hora de crear una compra
    @Test
    @Sql("classpath:datos.sql")
    public void agregarTest(){

        //Usuario usuario = usuarioRepo.getById("123");
        //DetalleCompra detalleCompra = detalleCompraRepo.getById(1);

        Usuario usuario = usuarioRepo.getOne("123");
        DetalleCompra detalleCompra = detalleCompraRepo.getOne(1);

        List<DetalleCompra> detalleCompraList = new ArrayList<>();
        detalleCompraList.add(detalleCompra);

        Compra compra = new Compra("PSE", usuario, detalleCompraList);

        Compra compra1 =  compraRepo.save(compra);

        Assertions.assertNotNull(compra1);

    }

    //Método test para verificar que se elimine correctamente una compra de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Compra compra = compraRepo.findById(1).orElse(null);

        compraRepo.delete(compra);

        Compra compra1 = compraRepo.findById(1).orElse(null);

        Assertions.assertNull(compra1);

    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de una compra
    @Test
    @Sql("classpath:datos.sql")
    public void modificarTest(){

        Compra compra = compraRepo.findById(1).orElse(null);

        compra.setMedioPago("Efecty");

        compraRepo.save(compra);
        Compra compra1 =compraRepo.findById(1).orElse(null);

        Assertions.assertEquals("Efecty", compra1.getMedioPago());

    }

    //Método test para listar las compras que se encuentran guardadas en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Compra> compraList = compraRepo.findAll();

        compraList.forEach(compra -> System.out.println(compra));

    }

    @Test
    @Sql("classpath:datos.sql")
    public void listaProductosCompradosTest(){

        Long totalProductos= compraRepo.obtenerCantidadProductosComprados("123");
        System.out.println("El total es: -----> "+totalProductos);
    }

}
