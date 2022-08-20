package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

//Pruebas unitarias Subasta
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Test registrar Subasta
    @Test
    @Sql("classpath:datos.sql")
    public void registrarTest(){
        //Producto productoGuardado = productoRepo.getById(1);
        Producto productoGuardado = productoRepo.getOne(1);

        Subasta subastaNuevo = new Subasta(LocalDateTime.now(),productoGuardado);

        Subasta subastaGuardado = subastaRepo.save(subastaNuevo);

        Assertions.assertNotNull(subastaGuardado);
    }

    //Test eliminar Subasta
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        Subasta guardado = subastaRepo.findById(3).orElse(null);

        Assertions.assertNotNull(guardado);

        subastaRepo.delete(guardado);

        Subasta guardado2 = subastaRepo.findById(3).orElse(null);

        Assertions.assertNull(guardado2);
    }

    //Test actualizar Subasta
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest(){

        Subasta guardado = subastaRepo.findById(2).orElse(null);
        //guardado.setProducto(productoRepo.getById(1));
        guardado.setProducto(productoRepo.getOne(1));

        subastaRepo.save(guardado);

        Subasta subastaBuscado = subastaRepo.findById(2).orElse(null);

        //Assertions.assertEquals(productoRepo.getById(1), subastaBuscado.getProducto());
        Assertions.assertEquals(productoRepo.getOne(1), subastaBuscado.getProducto());
    }

    //Test listar Subasta
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<Subasta> subastas = subastaRepo.findAll();

        subastas.forEach(u -> System.out.println(u));
    }
}
