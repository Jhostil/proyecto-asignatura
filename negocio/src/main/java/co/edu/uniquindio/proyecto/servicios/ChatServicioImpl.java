package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServicioImpl implements ChatServicio{

    private final ChatRepo chatRepo;

    public ChatServicioImpl(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    @Override
    public Chat nuevoChat(Chat chat) {
        return chatRepo.save(chat);
    }

    @Override
    public List<Chat> listarChats(String uCodigo) {
        return chatRepo.listarChats(uCodigo);
    }

    @Override
    public List<Mensaje> listarMensajes(Integer chatCodigo) {
        return chatRepo.listarMensajes(chatCodigo);
    }
}
