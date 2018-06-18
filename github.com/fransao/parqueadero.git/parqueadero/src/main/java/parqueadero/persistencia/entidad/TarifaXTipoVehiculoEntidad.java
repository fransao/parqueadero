package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity (name="TarifaXTipoVehiculo")
@NamedQuery (name="TarifaXTipoVehiculo.obtenerTarifaVehiculo", 
query="SELECT tarifaTipoVehiculo FROM TarifaXTipoVehiculo tarifaTipoVehiculo WHERE tarifaTipoVehiculo.tarifaXTipoVehiculoPK.tipoVehiculo = :tipovehiculo")
public class TarifaXTipoVehiculoEntidad implements Serializable {

    private static final long serialVersionUID = 1146366245398745026L;

    @EmbeddedId
    private TarifaXTipoVehiculoEntidadPK tarifaXTipoVehiculoPK;
    
    @Column(nullable = false)
    private Float valor;
    
    public TarifaXTipoVehiculoEntidadPK getTarifaXTipoVehiculoPK() {
        return tarifaXTipoVehiculoPK;
    }
    
    public void setTarifaXTipoVehiculoPK(TarifaXTipoVehiculoEntidadPK tarifaXTipoVehiculoPK) {
        this.tarifaXTipoVehiculoPK = tarifaXTipoVehiculoPK;
    }
    
    public Float getValor() {
        return valor;
    }
    
    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
