package parqueadero.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TarifaXTipoVehiculo.class)
public class TarifaXTipoVehiculoPK {

    @Id
    private TipoVehiculoEntidad tipoVehiculoEntidad;
    @Id
    private UnidadTiempoEntidad unidadTiempoEntidad;
    
    public TipoVehiculoEntidad getTipoVehiculoEntidad() {
        return tipoVehiculoEntidad;
    }
    
    public void setTipoVehiculoEntidad(TipoVehiculoEntidad tipoVehiculoEntidad) {
        this.tipoVehiculoEntidad = tipoVehiculoEntidad;
    }
    
    public UnidadTiempoEntidad getUnidadTiempoEntidad() {
        return unidadTiempoEntidad;
    }
    
    public void setUnidadTiempoEntidad(UnidadTiempoEntidad unidadTiempoEntidad) {
        this.unidadTiempoEntidad = unidadTiempoEntidad;
    }
    
}
