package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdminServicio {

    Administrador iniciarSesion(String email, String password) throws Exception;

    Integer cantUsuarios();

    Integer cantVentas();

    Integer cantProductosPorCategorias(Integer codigo);

    Integer ventasMes(Integer anio, Integer mes);
}
