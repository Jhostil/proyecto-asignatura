package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select count(distinct d.producto)  from Compra c join c.detalleCompraList d where c.usuario.codigo = :codigo")
    Long obtenerCantidadProductosComprados (String codigo);

    @Query("select sum(d.precioProducto*d.unidades) from Compra c join c.detalleCompraList d where c.usuario.codigo = :codigo")
    Long calcularTotalCompras(String codigo);

    @Query("select c, d from Compra c join c.detalleCompraList d where c.usuario.codigo = :codigo")
    List<Object[]> obtenerComprasUsuario(String codigo);//Nota: crear DTO
}
