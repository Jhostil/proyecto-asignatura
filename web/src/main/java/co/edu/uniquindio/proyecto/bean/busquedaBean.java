package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class busquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Setter @Getter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @Setter @Getter
    @Value("#{param['categoria']}")
    private String filtroCategoria;

    @Getter @Setter
    private boolean categoria = false;

    @PostConstruct
    public void inicializar(){

        if (filtroCategoria != null && !filtroCategoria.isEmpty()){
            productos = productoServicio.buscarProductos(busquedaParam, filtroCategoria);
            categoria = true;
        }else {
            if (busquedaParam != null && !busquedaParam.isEmpty()){
                productos = productoServicio.buscarProductos(busquedaParam, null);
                categoria = false;
            }
        }
    }

    public String buscar(){
        return "resultados_busqueda?faces-redirect=true&amp;busqueda=" + busqueda;
    }

    public String buscarPorCategoria(){
        return "resultados_busqueda?faces-redirect=true&amp;categoria=" + filtroCategoria;
    }
}
