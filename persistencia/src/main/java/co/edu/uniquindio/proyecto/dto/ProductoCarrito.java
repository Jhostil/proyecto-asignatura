package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private Integer id, unidades;
    private String nombre, imagen;
    private double precio;
}
