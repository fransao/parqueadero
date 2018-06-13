package parqueadero.persistencia.entidad;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="GestionVehiculo")
@NamedQuery (name="GestionVehiculo.obtenerGestionVehiculo", query="SELECT gestionVehiculo FROM GestionVehiculo gestionVehiculo WHERE gestionVehiculo : gestionhehiculo")
public class GestionVehiculoEntidad {

    @EmbeddedId
    private GestionVehiculoEntidadPK gestionVehiculoEntidadPK;
    
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaSalida;
    
    private EstadoParqueoEntidad estadoParqueoEntidad;
    
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

    public EstadoParqueoEntidad getEstadoParqueoEntidad() {
        return estadoParqueoEntidad;
    }

    public void setEstadoParqueoEntidad(EstadoParqueoEntidad estadoParqueoEntidad) {
        this.estadoParqueoEntidad = estadoParqueoEntidad;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
