package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto producto)throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException;

    List<Producto> listarProductos(Categoria categoria) ;

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String filtro) ;

    List<Producto> listarProductosUsuario(String codigoUsuario) throws Exception;

    List<Producto> listarTodosProductos() throws Exception;

    List<Producto> obtenerMisProductos(String codigo) throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(int id) throws Exception;

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productoCarritos, String medioPago) throws Exception;

    Integer cantProductos();

    List<Producto> listarRangoPrecio(double min, double max) throws Exception;

    List<Producto> listarPorCiudad(String nombreCiudad) throws Exception;

    List<Producto> listarRangoUnidades(int min, int max) throws Exception;
}
