package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//Padre persona
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@ToString
public class Persona implements Serializable {

    //Llave primaria de la entidad
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    //Atributo nombre de la persona
    @Column(nullable = false,length = 150)
    @Length(min = 2, max = 150, message = "El nombre debe tener mínimo 2 caracteres y máximo 150")
    @NotBlank(message = "El campo está vacío, debe ingresar un nombre")
    private String nombre;

    //Atributo email de la persona
    @Column(nullable = false, unique = true)
    @Email(message = "Ingrese un email válido")
    @NotBlank(message = "El campo email no sebe estar vacío")
    private String email;

    //Atributo contrasena de la persona
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El campo está vacío, debe ingresar una password")
    @Length(max = 100, message = "La contraseña no debe tener más de 100 carcteres")
    //@JsonIgnore
    private String password;
}
