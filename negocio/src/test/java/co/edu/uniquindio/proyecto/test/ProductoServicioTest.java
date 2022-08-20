package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void obtenerProductoTest(){
        Usuario vendedor = null;
        try {
            vendedor = usuarioServicio.obtenerUsuario("123");
            LocalDate localDate = LocalDate.of(2021,11,25);
            Producto producto = new Producto("Televidor LG 55 ´´", "Maravilloso televisor", 10, "descripcion", 2000000F, 0F, localDate, vendedor );

            Producto publicado = productoServicio.publicarProducto(producto);

            Assertions.assertNotNull(publicado);

        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }

    }

    @Test
    public void obtenerCompradosTest() throws Exception {
        List<Producto> comprados = usuarioServicio.listarComprados("123");
        comprados.forEach(System.out::println);
    }
}
