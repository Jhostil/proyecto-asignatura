package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

//Entidad Compra

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Compra implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para guardar la hora y fecha de cuando se hace la compra
    @Column(name = "fechaCompra", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompra;

    //Atributo que sirve para guardar el medio por el cual se realiza el pago
    @NotBlank(message = "El campo Pago es obligatorio")
    @Size(max = 50)
    @Column(name = "medioPago", length = 50, nullable = false)
    private String medioPago;


    //Relacion de Compra con Usuario de muchos a uno
    @ToString.Exclude
    @ManyToOne
    private Usuario usuario;

    //Relación con la entidad DetalleComrpa, donde indica que la Compra tiene un lista de DetallesCompra
    @OneToMany(mappedBy = "compra")
    @JsonIgnore
    private List<DetalleCompra> detalleCompraList;

    public Compra(String medioPago, Usuario usuario, List<DetalleCompra> detalleCompraList) {
        this.medioPago = medioPago;
        this.usuario = usuario;
        this.detalleCompraList = detalleCompraList;
    }
}
