package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class SubastaBean implements Serializable {

    @Autowired
    private SubastaServicio subastaServicio;

    @Getter
    @Setter
    private List<Subasta> subastas;

    @PostConstruct
    public void inicializar() throws Exception{
        this.subastas = subastaServicio.listarSubastasDisponibles();
    }
}
