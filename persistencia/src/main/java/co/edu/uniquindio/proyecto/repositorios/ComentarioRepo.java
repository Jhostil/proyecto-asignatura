package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("select c from Comentario c where c.calificacion between :calificacionMenor and :calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);

    @Query("select c from Producto p join p.comentarios c where p.codigo = :id")
    List<Comentario> obtenerComentarios1(Integer id);
    //Lo mismo
    @Query("select c from Comentario c where c.producto.codigo = :id")
    List<Comentario> obtenerComentarios2(Integer id);
}
