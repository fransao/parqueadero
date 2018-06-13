package parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity (name="EstadoParqueo")
@NamedQuery (name="EstadoParqueo.obtenerEstadoParqueo", query="SELECT estadoParqueo FROM EstadoParqueo estadoParqueo WHERE estadoParqueo.estado = : estado")
public class EstadoParqueoEntidad {

    @Id
    private Integer estado;
    
    @Column (nullable = false)
    private String nombreEstado;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
}
