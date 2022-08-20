package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {

    @Query(value = "select c from Chat c where c.usuario.codigo = :uCodigo")
    List<Chat> listarChats(String uCodigo);

    @Query(value = "select c from Chat c where c.producto.codigo = :pCodigo")
    List<Chat> listarChatsVendedor(String pCodigo);

    @Query("select m from Mensaje m where m.chat.codigo = :chatCodigo order by m.fecha desc")
    List<Mensaje> listarMensajes(Integer chatCodigo);
}
