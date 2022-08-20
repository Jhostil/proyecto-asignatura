package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//Entidad Chat
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Chat implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Relación muchos a uno con la entidad usuario
    @ToString.Exclude
    @ManyToOne
    private Usuario usuario;

    //Relación muchos a uno con la entidad Producto
    @ToString.Exclude
    @ManyToOne
    private Producto producto;

    //Relación uno a muchos con la entidad Mensaje
    @ToString.Exclude
    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private List<Mensaje> mensajes;


    public Chat(Usuario usuario, Producto producto, List<Mensaje> mensajes) {
        this.usuario = usuario;
        this.producto = producto;
        this.mensajes = mensajes;
    }
}
