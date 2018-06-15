package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TarifaXTipoVehiculoPK implements Serializable {

    private static final long serialVersionUID = -538256606802120246L;
    
    private Integer tipoVehiculo;
    private Integer unidadTiempo;
    
    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }
    
    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public Integer getUnidadTiempo() {
        return unidadTiempo;
    }
    
    public void setUnidadTiempoEntidad(Integer unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TarifaXTipoVehiculoPK)) {
            return false;
        }
        
        TarifaXTipoVehiculoPK castOther = (TarifaXTipoVehiculoPK) other;
        
        return this.getTipoVehiculo().equals(castOther.getTipoVehiculo()) && this.getUnidadTiempo().equals(castOther.getUnidadTiempo());
        
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.tipoVehiculo.hashCode();
        hash = hash * prime + this.unidadTiempo.hashCode();

        return hash;
    }
}
