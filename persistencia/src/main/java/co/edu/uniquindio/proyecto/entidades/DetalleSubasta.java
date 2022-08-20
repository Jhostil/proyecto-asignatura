package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

//Entidad DetalleSubasta

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleSubasta implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para guardar el valor del producto de la subasta
    @Column(nullable = false, precision = 9, scale = 2)
    @Positive(message = "El valor debe ser positivo")
    private Double valor;

    //Fecha de la subasta
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaSubasta;

    //Relacion de DetalleSubasta con Usuario de muchos a uno
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

    //Relacion de DetalleSubasta con Subasta de muchos a uno
    @ManyToOne
    @ToString.Exclude
    private Subasta subasta;

    public DetalleSubasta(Double valor, LocalDateTime fechaSubasta, Usuario usuario, Subasta subasta) {
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
        this.usuario = usuario;
        this.subasta = subasta;
    }
}
