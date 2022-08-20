package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminServicioImpl implements AdminServicio{

    private final AdministradorRepo adminRepo;

    public AdminServicioImpl (AdministradorRepo adminRepo) {
        this.adminRepo=adminRepo;
    }

    @Override
    public Administrador iniciarSesion(String email, String password) throws Exception {

        return adminRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrecots"));

    }

    @Override
    public Integer cantUsuarios() {
        return adminRepo.cantUsuarios();
    }

    @Override
    public Integer cantVentas(){
        return adminRepo.cantVentas();
    }

    @Override
    public Integer cantProductosPorCategorias(Integer codigo){
        return adminRepo.cantProductosPorCategorias(codigo);
    }

    @Override
    public Integer ventasMes(Integer anio, Integer mes){
        return adminRepo.ventasMes(anio, mes);
    }
}
