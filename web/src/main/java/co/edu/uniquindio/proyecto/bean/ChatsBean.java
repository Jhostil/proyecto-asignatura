package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ChatServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ChatsBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ChatServicio chatServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter
    @Setter
    private List<Chat> chats;

    @Getter
    @Setter
    private List<Mensaje> misMensajes;

    @PostConstruct
    public void inicializar(){
        this.chats=chatServicio.listarChats(usuarioSesion.getCodigo());
        this.misMensajes=chatServicio.listarMensajes(chats.get(0).getCodigo());
    }

    public void seleccionarChat (Chat chat){
        misMensajes=chatServicio.listarMensajes(chat.getCodigo());
    }

}
