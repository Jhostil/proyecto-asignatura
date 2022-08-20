package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

//Entidad Administrador que hereda atributos y métodos de la entidad Persona

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class Administrador extends Persona implements Serializable {

    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
