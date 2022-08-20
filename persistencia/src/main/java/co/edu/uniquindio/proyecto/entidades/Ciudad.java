package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//Entidad que corresponde a la Ciudad

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {


    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para darle nombre a una ciudad cuando se crea
    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(max = 80, message = "El tamaño maximo debe ser de 80 caracteres")
    @Column(name = "nombre", length = 80, nullable = false)
    private String nombre;

    //Relación entre la entidad Ciudad y Usuario, donde indica que la ciudad tiene una lista de usuarios
    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<Usuario> usuarios;

    //Relación entre la entidad Ciudad y Producto, donde indica que la ciudad tiene una lista de productos
    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<Producto> productos;

    //Constructor sobrecargado de la entidad que recibe por parámetro el nombre
    public Ciudad (String nombre){
        this.nombre=nombre;
    }
}
