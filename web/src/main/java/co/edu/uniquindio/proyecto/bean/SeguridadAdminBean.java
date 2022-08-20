package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadAdminBean implements Serializable {

    @Getter
    @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Autowired
    private AdminServicio adminServicio;

    @Getter @Setter
    private Administrador adminSesion;

    public String iniciarSesion(){

        if(!email.isEmpty() && !password.isEmpty()){
            try {
                adminSesion = adminServicio.iniciarSesion(email, password);
                autenticado=true;

                return "/admin/inicioAdmin.xhtml?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/admin/inicioAdmin.xhtml=faces-redirect=true";
    }
}
