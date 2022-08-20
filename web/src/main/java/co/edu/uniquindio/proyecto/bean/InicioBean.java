package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.servicios.EmailService;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @PostConstruct
    public void inicializar() throws Exception{
        Categoria categoria = categoriaRepo.findById(2).orElseThrow(() -> new Exception("No se encontró una categoría con ese id"));
        this.productos = productoServicio.listarProductos(categoria);

    }

    public String irADetalle(String id){
        return "detalle_producto?faces-redirect=true&amp;producto=" + id;
    }

}
