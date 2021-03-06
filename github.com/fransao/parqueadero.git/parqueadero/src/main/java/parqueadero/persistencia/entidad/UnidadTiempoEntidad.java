package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity (name="UnidadTiempo")
@NamedQuery (name="UnidadTiempo.obtenerUnidadTiempo", query="SELECT unidadTiempo FROM UnidadTiempo unidadTiempo WHERE unidadTiempo.unidadTiempo = :unidadtiempo")
public class UnidadTiempoEntidad implements Serializable {

    private static final long serialVersionUID = -7620880802431560767L;

    @Id
    private Integer unidadTiempo;

    @Column(nullable = true)
    private String nombreUnidadTiempo;

    public Integer getUnidadTiempo() {
        return unidadTiempo;
    }

    public void setUnidadTiempo(Integer unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }

    public String getNombreUnidadTiempo() {
        return nombreUnidadTiempo;
    }

    public void setNombreUnidadTiempo(String nombreUnidadTiempo) {
        this.nombreUnidadTiempo = nombreUnidadTiempo;
    }
    
}
