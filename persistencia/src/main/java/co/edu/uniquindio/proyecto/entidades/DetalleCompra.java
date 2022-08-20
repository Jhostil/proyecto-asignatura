package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

//Entidad DetalleCompra

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;


    //Atributo que sirve para guardar las unidades que se desean comprar
    @Column(name = "unidades", nullable = false)
    @Positive(message = "Debe ingresar mínimo 1 unidad")
    @Max(value = 20, message = "Deben ser máximo 20 unidades")
    private int unidades;

    //Atributo que sirve para guardar el precio del producto a comprar
    @Column(name = "precioProducto", nullable = false)
    @Positive(message = "El precio debe ser positivo")
    private double precioProducto;

    //Relacion de DetalleCompra con Producto de muchos a uno
    @ToString.Exclude
    @ManyToOne
    private Producto producto;

    //Relacion de DetalleCompra con Compra de muchos a uno
    @ToString.Exclude
    @ManyToOne
    private Compra compra;

    public DetalleCompra(int unidades, double precioProducto, Producto producto, Compra compra) {
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.compra = compra;
    }
}
