package parqueadero.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(GestionVehiculoEntidad.class)
public class GestionVehiculoEntidadPK {

    @Id
    private VehiculoEntidad vehiculoEntidad;
    
    @Id
    private TipoVehiculoEntidad tipoVehiculoEntidad;
    
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaIngreso;
    
    public VehiculoEntidad getVehiculoEntidad() {
        return vehiculoEntidad;
    }
    
    public void setVehiculoEntidad(VehiculoEntidad vehiculoEntidad) {
        this.vehiculoEntidad = vehiculoEntidad;
    }
    
    public TipoVehiculoEntidad getTipoVehiculoEntidad() {
        return tipoVehiculoEntidad;
    }
    
    public void setTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculoEntidad) {
        this.tipoVehiculoEntidad = tipoVehiculoEntidad;
    }
    
    public java.util.Date getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(java.util.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
}
