package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubastaServicioImpl implements SubastaServicio{

    @Autowired
    private SubastaRepo subastaRepo;

    @Override
    public List<Subasta> listarSubastasDisponibles(){
        return subastaRepo.listarSubastasDisponibles();
    }
}
