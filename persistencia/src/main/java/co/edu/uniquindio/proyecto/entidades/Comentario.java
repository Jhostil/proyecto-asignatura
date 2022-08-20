package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Entidad Comentario

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para guardar el mensaje del comentario
    @NotBlank(message = "El campo Mensaje es obligatorio")
    @Size(max = 500, message = "El tamaño maximo debe ser de 500 caracteres")
    @Column(name = "mensaje", length = 500, nullable = false)
    private String mensaje;

    //Atributo que sirve para guardar la respuesta del comentario
    @Size(max = 500, message = "El tamaño maximo debe ser de 500 caracteres")
    @Column(name = "respuesta", length = 500)
    private String respuesta;

    //Atributo que sirve para guardar la hora y fecha de cuando se hizo el comentario
    @Column(name = "fechaComentario", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    //Atributo que sirve para guardar la calificación que asigna el usuario
    @Column(name = "calificacion", nullable = false)
    @PositiveOrZero
    @Max(value = 5, message = "La Calificacion debe ser menor o igual a 5")
    private int calificacion;

    //Relacion de Comentario con Usuario de muchos a uno
    @ToString.Exclude
    @ManyToOne
    private Usuario usuario;

    //Relacion de Comentario con Producto de muchos a uno
    @ToString.Exclude
    @ManyToOne
    private Producto producto;

    public Comentario (String mensaje, int calificacion, Usuario usuario, Producto producto)
    {
        this.mensaje = mensaje;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.producto = producto;
    }

    public String getFechaEstilo(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd, MM YYYY");
        return fechaComentario.format(DateTimeFormatter.ISO_DATE);
    }
}
