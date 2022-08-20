package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//Entidad Categoría
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Categoria implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para asignarle nombre a una categoría
    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(max = 50, message = "El tamaño maximo debe ser de 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    //Relación muchos a muchos con la entidad Producto
    @ToString.Exclude
    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Producto> productos;

    //Constructor sobrecargado que recibe por parámetro el nombre de la categoría
    public Categoria (String nombre){
        this.nombre=nombre;
    }
}
