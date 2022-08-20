package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;


    @GetMapping
    public List<Producto> listar(){
        try {
            return productoServicio.listarTodosProductos();
        } catch (Exception e) {
           return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable("id") int idProducto) {
        try {
            Producto producto = productoServicio.obtenerProducto(idProducto);
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){



        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new Mensaje("El producto se creó exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Producto producto) {
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se actualizó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminarProducto (@PathVariable("codigo") int codigo) {
        try {
            productoServicio.eliminarProducto(codigo);
            return ResponseEntity.status(200).body(new Mensaje("El producto se eliminó exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> obtenerPorCategoria(@RequestBody Categoria categoria){
        try {
            List<Producto> lista = productoServicio.listarProductos(categoria);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al obtener la lista"));
        }
    }

    @GetMapping("/precio/{min},{max}")
    public ResponseEntity<?> obtenerPorCategoria(@PathVariable("min") double min, @PathVariable("max") double max){
        try {
            List<Producto> lista = productoServicio.listarRangoPrecio(min, max);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al obtener la lista"));
        }
    }

    @GetMapping("/ciudad/{nombre}")
    public ResponseEntity<?> obtenerPorCiudad(@PathVariable("nombre") String nombre){
        try {
            List<Producto> lista = productoServicio.listarPorCiudad(nombre);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al obtener la lista"));
        }
    }

    //Obtiene los productos dado el id de un vendedor
    @GetMapping("/vendedor/{id}")
    public ResponseEntity<?> obtenerProductosVendedor(@PathVariable("id") String id){
        try {
            List<Producto> lista = productoServicio.obtenerMisProductos(id);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al obtener la lista"));
        }
    }

    //Lista los productos que dado un rango, las unidades estén en ese rango
    @GetMapping("/unidades/{min},{max}")
    public ResponseEntity<?> obtenerPorRangoCantidad(@PathVariable("min") int min, @PathVariable("max") int max){
        try {
            List<Producto> lista = productoServicio.listarRangoUnidades(min, max);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al obtener la lista"));
        }
    }
}
