package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Entidad Producto
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    //Llave primaria de la entidad que es autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Atributo nombre del producto
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Length(max = 100, message = "El nombre debe tener máximo 100 caracteres")
    private String nombre;

    //Atributo nombre del producto
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre de la publicación es obligatorio")
    @Length(max = 100, message = "El nombre de la publicación debe tener máximo 100 caracteres")
    private String nombrePublicacion;

    //Atributo unidades disponibles de un producto
    @Column(nullable = false)
    @PositiveOrZero
    private int unidades;

    //Atributo descripcion de un producto
    @Column(nullable = false, length = 1000)
    @NotBlank(message = "La descripción del producto es obligatoria")
    @Length(max = 1000, message = "La descripción debe tener máximo 1000 caracteres")
    private String descripcion;

    //Atributo precio del producto en el instante actual
    @Column(nullable = false, precision = 9, scale = 2)
    @Positive (message = "El precio debe ser positivo")
    private double precio;

    //Atributo descuento del producto
    @Column(nullable = false, precision = 3, scale = 2)
    @PositiveOrZero
    private double descuento;

    //Atributo descuento del producto
    @Column(nullable = false, precision = 3, scale = 2)
    @PositiveOrZero
    private double descuentoVisible = 0;

    //Atributo de la fecha limite del producto
    @Column(nullable = false)
    @Future(message = "La fecha debe ser futura")
    private LocalDate fechaLimite;

    //Relacion de muchos a uno con Usuario
    @ToString.Exclude
    @ManyToOne
    private Usuario vendedor;

    //Relacion de muchos a uno con Ciudad
    @ToString.Exclude
    @ManyToOne
    private Ciudad ciudad;

    //Lista de rutas para las imagenes
    @ToString.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> rutaImagen;

    //Relacion de uno a muchos con DetalleCompra
    @ToString.Exclude
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    //Relacion de uno a muchos con Subasta
    @ToString.Exclude
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Subasta> subastas;

    //Relacion de uno a muchos con Comentario
    @ToString.Exclude
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Comentario> comentarios;

    //Relacion de uno a muchos con Chat
    @ToString.Exclude
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Chat> chats;

    //Relacion de muchos a muchos con Usuario
    @ToString.Exclude
    @ManyToMany(mappedBy = "productosFavoritos")
    @JsonIgnore
    private List<Usuario> usuarios;

    //Relacion de muchos a muchos con Categoria
    @ToString.Exclude
    @ManyToMany
    @JsonIgnore
    private List<Categoria> categorias;


    public Producto(String nombre,String nombrePublicacion, int unidades, String descripcion, double precio, double descuento, LocalDate fechaLimite, Usuario vendedor) {
        this.nombre = nombre;
        this.nombrePublicacion = nombrePublicacion;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;

    }

    public String getImagenPrincipal (){
        if(rutaImagen != null && !rutaImagen.isEmpty()){
            return rutaImagen.get(0);
        }
        return "default.jpg";
    }

    public double getDescuentoVisible(){
        return  (precio-(precio*(descuento/100)));
    }

    public List<String> getNombreCategorias (){

        List<String> nombreCategorias = new ArrayList<>();

        for (Categoria categoria: categorias ) {
            nombreCategorias.add(categoria.getNombre());
        }
        return nombreCategorias;
    }
}
