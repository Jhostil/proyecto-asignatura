package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {

    Optional<Administrador> findByEmailAndPassword (String email, String clave);

    @Query("select count(u) from Usuario u")
    Integer cantUsuarios();

    @Query("select count(c) from Compra c")
    Integer cantVentas();

    @Query("select count(p) from Categoria c join c.productos p where c.codigo = :codigo")
    Integer cantProductosPorCategorias(Integer codigo);

    @Query("select count(c) from Compra c where year(c.fechaCompra) = :anio and month(c.fechaCompra) = :mes ")
    Integer ventasMes(Integer anio, Integer mes);


}
