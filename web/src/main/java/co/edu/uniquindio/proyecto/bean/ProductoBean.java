package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private ArrayList<String> imagenes;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Value("${upload.url}")
    private String urlUpload;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private ArrayList<String> mediosPago;

    @PostConstruct
    public void inicializar(){

        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        this.categorias = productoServicio.listarCategorias();
        this.ciudades = ciudadServicio.listarCiudades();
        mediosPago = new ArrayList<>();
        mediosPago.add("PSE");
        mediosPago.add("Tarjeta de crédito");
        mediosPago.add("Tarjera débito");
        mediosPago.add("Efecty");
        mediosPago.add("Baloto");
        mediosPago.add("PayPal");
        mediosPago.add("PayU");
    }

    public void crearProducto(){
        try {
            if(usuarioSesion != null){
                if(!imagenes.isEmpty()){
                    producto.setVendedor(usuarioSesion);
                    producto.setRutaImagen(imagenes);
                    producto.setFechaLimite(LocalDate.now().plusMonths(2));
                    productoServicio.publicarProducto(producto);
                    producto = new Producto();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto creado con éxito");
                    FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es necesario subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
                }
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
        }
    }

    public void subirImagenes (FileUploadEvent fileUploadEvent) {
        UploadedFile imagen = fileUploadEvent.getFile();
        String nombreImg = subirImagen(imagen);

        if (nombreImg != null){
            imagenes.add(nombreImg);
        }
    }

    public String subirImagen (UploadedFile imagen){

        try {
            File archivo = new File(urlUpload + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo); //Donde quiero que copie el archivo
            IOUtils.copy(imagen.getInputStream(), outputStream );
            return imagen.getFileName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
