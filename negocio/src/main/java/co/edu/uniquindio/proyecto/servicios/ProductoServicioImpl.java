package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final ComentarioRepo comentarioRepo;

    private final CategoriaRepo categoriaRepo;

    private final CompraRepo compraRepo;

    private final DetalleCompraRepo detalleCompraRepo;
    
    @Autowired
    private EmailService emailService;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, CategoriaRepo categoriaRepo, CompraRepo compraRepo, DetalleCompraRepo detalleCompraRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.categoriaRepo = categoriaRepo;
        this.compraRepo = compraRepo;
        this.detalleCompraRepo = detalleCompraRepo;

    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {
        try {
            Producto producto1 = productoRepo.getById(producto.getCodigo());
            producto1 = producto;
            productoRepo.save(producto1);
        } catch (Exception e){
            throw new Exception("Error al actualizar el producto");
        }

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigo);

        if (producto.isEmpty()) {
            throw new Exception("El código del producto no existe");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigo).orElseThrow(() -> new ProductoNoEncontradoException("El código del producto no es válido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {

        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {

        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }
    @Override
    public Integer cantProductos(){
        return productoRepo.cantProductos();
    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String filtro) {

        List<Producto> productos = new ArrayList<>();
        if(filtro != null){

                Categoria categoria = categoriaRepo.buscarPorNombre(filtro);

                productos = productoRepo.listarPorCategoria(categoria);

        }
        else{
            productos = productoRepo.buscarProductosPorNombre(nombreProducto);
        }
        return productos;

    }

    @Override
    public List<Producto> listarProductosUsuario(String codigoUsuario) throws Exception {
        return null;
    }

    @Override
    public List<Producto> listarTodosProductos() throws Exception {
        return productoRepo.findAll();
    }

    @Override
    public List<Producto> obtenerMisProductos(String codigo) throws Exception {
        return productoRepo.obtenerMisProductos(codigo);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(int id) throws Exception {
        return categoriaRepo.findById(id).orElseThrow(() -> new Exception("No se encontró una categoría con ese id"));
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception{
        
        String cuerpoEmail = "Cordial saludo \n" + usuario.getNombre() + "\n\n" +
                "A continuación encontrará los detalles de su compra. \n\n";

        try {

            verificarExistencias(productos);
            Compra compra = new Compra();
            compra.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
            compra.setUsuario(usuario);
            compra.setMedioPago(medioPago);
            Compra compraGuardada = compraRepo.save(compra);


            DetalleCompra dc;
            for (ProductoCarrito p : productos){
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades((p.getUnidades()));
                dc.setProducto(productoRepo.findById(p.getId()).get());

                int unidadesNuevas = productoRepo.findById(p.getId()).get().getUnidades() - p.getUnidades();
                Producto productoAux = productoRepo.findById(p.getId()).orElse(null);
                productoAux.setUnidades(unidadesNuevas);
                productoRepo.save(productoAux);
                detalleCompraRepo.save(dc);
                
                cuerpoEmail += "-Producto: " + p.getNombre() + "\n Unidades: " + p.getUnidades() + "\n\n";
            }
            cuerpoEmail += "Medio de pago: " + medioPago +
            "\n\nGracias por preferirnos.";
            emailService.sendSimpleEmail(usuario.getEmail(),cuerpoEmail,"UniShop-Confirmación de compra");
            return compraGuardada;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<Producto> listarRangoPrecio(double min, double max) throws Exception {
        return productoRepo.listarRangoPrecio(min, max);
    }

    @Override
    public List<Producto> listarPorCiudad(String nombreCiudad) throws Exception {
        return productoRepo.listarPorCiudad(nombreCiudad);
    }

    @Override
    public List<Producto> listarRangoUnidades(int min, int max) throws Exception {
        return productoRepo.listarRangoUnidades(min, max);
    }

    public boolean verificarExistencias(ArrayList<ProductoCarrito> productos) throws Exception{

        for (ProductoCarrito p : productos){
            if(productoRepo.findById(p.getId()).get().getUnidades() < p.getUnidades()){
                throw new Exception("El producto '" + productoRepo.findById(p.getId()).get().getNombre() + "' tiene " + productoRepo.findById(p.getId()).get().getUnidades() + " unidades disponibles");
            }
        }

        return true;
    }

}
