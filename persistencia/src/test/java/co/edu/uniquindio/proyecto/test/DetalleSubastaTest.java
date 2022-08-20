package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.DetalleSubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

//Pruebas unitarias DetalleSubasta
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleSubastaTest {

    @Autowired
    private DetalleSubastaRepo detalleSubastaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    //Test registrar DetalleSubasta
    @Test
    @Sql("classpath:datos.sql")
    public void registrarTest(){
        Usuario usuarioGuardado = usuarioRepo.findById("123").orElse(null);
        Subasta subastaGuardado = subastaRepo.findById(2).orElse(null);

        DetalleSubasta detalleSubastaNuevo = new DetalleSubasta((double)5300900, LocalDateTime.now(),usuarioGuardado,subastaGuardado);

        DetalleSubasta detalleSubastaGuardado = detalleSubastaRepo.save(detalleSubastaNuevo);

        Assertions.assertNotNull(detalleSubastaGuardado);
    }

    //Test eliminar DetalleSubasta
    @Test
    @Sql("classpath:datos.sql")
    public void eliminarTest(){

        DetalleSubasta guardado = detalleSubastaRepo.findById(2).orElse(null);

        Assertions.assertNotNull(guardado);

        detalleSubastaRepo.delete(guardado);

        DetalleSubasta guardado2 = detalleSubastaRepo.findById(2).orElse(null);

        Assertions.assertNull(guardado2);
    }

    //Test actualizar DetalleSubasta
    @Test
    @Sql("classpath:datos.sql")
    public void actualizarTest(){

        DetalleSubasta guardado = detalleSubastaRepo.findById(3).orElse(null);
        guardado.setValor((double)35000);

        detalleSubastaRepo.save(guardado);

        DetalleSubasta detalleSubastaBuscado = detalleSubastaRepo.findById(3).orElse(null);

        Assertions.assertEquals((double)35000, detalleSubastaBuscado.getValor());
    }

    //Test listar DetalleSubasta
    @Test
    @Sql("classpath:datos.sql")
    public void listarTest(){

        List<DetalleSubasta> detalleSubastas = detalleSubastaRepo.findAll();

        detalleSubastas.forEach(u -> System.out.println(u));
    }
}
