package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ChatServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private String precioVisible, descuentoVisible;

    @Getter
    @Setter
    private Chat nuevoChat;

    @Autowired
    private ChatServicio chatServicio;

    @PostConstruct
    public void inicializar(){

        this.nuevoChat=new Chat();

        this.nuevoComentario = new Comentario();
        if (codigoProducto != null && !codigoProducto.isEmpty()){
            Integer codigo = Integer.parseInt(codigoProducto);
            producto = productoServicio.obtenerProducto(codigo);
            this.comentarios = producto.getComentarios();
        }


        double precio = producto.getPrecio();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        precioVisible = formatter.format(precio);
        if (producto.getDescuento() > 0){
            double descuentoProducto = producto.getDescuentoVisible();
            descuentoVisible = formatter.format(descuentoProducto);
        }

    }

    public void crearComentario (){
        {
            try {
                if (usuarioSesion != null){
                    nuevoComentario.setProducto(producto);
                    nuevoComentario.setUsuario(usuarioSesion);
                    productoServicio.comentarProducto(nuevoComentario);
                    this.comentarios.add(nuevoComentario);
                    nuevoComentario = new Comentario();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public String crearChat (){

        try {
            if (usuarioSesion != null){
                nuevoChat.setProducto(producto);
                nuevoChat.setUsuario(usuarioSesion);
                chatServicio.nuevoChat(nuevoChat);

                return "/usuario/chats.xhtml";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public double calificacionPromedio (){
        return 0;
    }

}
