package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;

import java.util.List;

public interface ChatServicio {

    Chat nuevoChat(Chat chat);

    List<Chat> listarChats(String uCodigo);

    List<Mensaje> listarMensajes(Integer chatCodigo);
}
