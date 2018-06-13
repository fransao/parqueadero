package parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity (name="TarifaXTipoVehiculo")
@NamedQuery (name="TarifaXTipoVehiculo.obtenerTarifaVehiculo", 
query="SELECT tarifaTipoVehiculo FROM TarifaTipoVehiculo tarifaTipoVehiculo WHERE tarifaTipoVehiculo.tipoVehiculo = :tipovehiculo")
public class TarifaXTipoVehiculo {

    @EmbeddedId
    private TarifaXTipoVehiculoPK tarifaXTipoVehiculoPK;
    @Column(nullable = false)
    private Float valor;
    
    public TarifaXTipoVehiculoPK getTarifaXTipoVehiculoPK() {
        return tarifaXTipoVehiculoPK;
    }
    
    public void setTarifaXTipoVehiculoPK(TarifaXTipoVehiculoPK tarifaXTipoVehiculoPK) {
        this.tarifaXTipoVehiculoPK = tarifaXTipoVehiculoPK;
    }
    
    public Float getValor() {
        return valor;
    }
    
    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
