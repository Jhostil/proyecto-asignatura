package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {

    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra d where d.producto.vendedor.codigo = :codigo")
    Long calcularTotalVentas(String codigo);
}
