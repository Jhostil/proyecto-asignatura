package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

//Entidad Subasta
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Subasta implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo de la fecha limite en la que se realizara la subasta
    @Future
    private LocalDateTime fechaLimite;

    //Relacion de muchos a uno con Producto
    @ToString.Exclude
    @ManyToOne
    private Producto producto;

    //Relacion de uno a muchos con Producto
    @ToString.Exclude
    @OneToMany(mappedBy = "subasta")
    @JsonIgnore
    private List<DetalleSubasta> detalleSubastas;

    public Subasta( LocalDateTime fechaLimite, Producto producto) {
        this.fechaLimite = fechaLimite;
        this.producto = producto;
    }
}
