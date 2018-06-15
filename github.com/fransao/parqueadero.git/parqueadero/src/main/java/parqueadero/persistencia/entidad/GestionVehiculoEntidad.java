package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="GestionVehiculo")
@NamedQuery (name="GestionVehiculo.obtenerGestionVehiculo", query="SELECT gestionVehiculo FROM GestionVehiculo gestionVehiculo WHERE gestionVehiculo = :gestionhehiculo")
public class GestionVehiculoEntidad implements Serializable {

    private static final long serialVersionUID = -6788333527455308654L;

    @EmbeddedId
    private GestionVehiculoEntidadPK gestionVehiculoEntidadPK;
    
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaSalida;
    
    @OneToOne
    @JoinColumn(name="estadoParqueo", referencedColumnName="estado")
    private EstadoParqueoEntidad estadoParqueo;
    
    private Float valor;

    public GestionVehiculoEntidadPK getGestionVehiculoEntidadPK() {
        return gestionVehiculoEntidadPK;
    }

    public void setGestionVehiculoEntidadPK(GestionVehiculoEntidadPK gestionVehiculoEntidadPK) {
        this.gestionVehiculoEntidadPK = gestionVehiculoEntidadPK;
    }

    public java.util.Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(java.util.Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public EstadoParqueoEntidad getEstadoParqueo() {
        return estadoParqueo;
    }

    public void setEstadoParqueo(EstadoParqueoEntidad estadoParqueo) {
        this.estadoParqueo = estadoParqueo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
