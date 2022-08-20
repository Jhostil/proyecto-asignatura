package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

//Entidad Mensaje

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Mensaje implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo que sirve para guardar el mensaje
    @Column(nullable = false, length = 450)
    @NotBlank(message = "Ingrese un mensaje")
    @Length(max = 450, message = "El mensaje no debe contener más de 450 caracteres")
    private String mensaje;

    @Column(nullable = false)
    private String emisor;

    //Fecha del mensaje al momento de ser enviado
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    //Relación muchos a uno con la entidad Chat
    @ManyToOne
    @ToString.Exclude
    private Chat chat;

    public Mensaje(String mensaje, String emisor, LocalDateTime fecha, Chat chat) {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
        this.chat = chat;
    }

    public boolean esComprador (){
        if (emisor.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
