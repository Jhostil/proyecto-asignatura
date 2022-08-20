package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//Clase para realizar la prueba unitaria del CRUD de la entidad DetalleCompra
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    //Repositorio de la entidad detalleCompra
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    //Repositorio de la entidad compra
    @Autowired
    private CompraRepo compraRepo;

    //Repositorio de la entidad producto
    @Autowired
    private ProductoRepo productoRepo;


    //Método test para comprobar la correcta ejecución a la hora de crear un detalle de una compra
    @Test
    @Sql("classpath:datos.sql")
    public void agregarTest(){

        Compra compra = compraRepo.findById(1).orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra = new DetalleCompra(2,20000, producto, compra );

        DetalleCompra detalleCompra1 = detalleCompraRepo.save(detalleCompra);

        Assertions.assertNotNull(detalleCompra1);
    }

    //Método test para verificar que se elimine correctamente un detalle de una compra de la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        DetalleCompra detalleCompra = detalleCompraRepo.findById(1).orElse(null);

        detalleCompraRepo.delete(detalleCompra);

        DetalleCompra detalleCompra1 = detalleCompraRepo.findById(1).orElse(null);

        Assertions.assertNull(detalleCompra1);

    }

    //Método test para verificar el correcto funcionamiento al momento de actualizar un dato de un detalle de una compra
    @Test
    @Sql("classpath:datos.sql")
    public void modificarTest(){

        DetalleCompra detalleCompra = detalleCompraRepo.findById(1).orElse(null);

        detalleCompra.setUnidades(3);

        detalleCompraRepo.save(detalleCompra);

        DetalleCompra detalleCompra1 = detalleCompraRepo.findById(1).orElse(null);

        Assertions.assertEquals(3, detalleCompra1.getUnidades());

    }

    //Método test para listar los detallesCompras que se encuentran guardados en la base de datos
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){
        List<DetalleCompra> detalleCompraList = detalleCompraRepo.findAll();

        detalleCompraList.forEach(detalleCompra -> System.out.println(detalleCompra));

    }

}
