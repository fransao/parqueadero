package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class GestionVehiculoEntidadPK implements Serializable {

    private static final long serialVersionUID = 1665044872151755691L;
    
    private String placa;
    private Integer tipoVehiculo;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaIngreso;
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public java.util.Date getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(java.util.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GestionVehiculoEntidadPK)) {
            return false;
        }
        GestionVehiculoEntidadPK castOther = (GestionVehiculoEntidadPK) other;
        return this.placa.equals(castOther.getPlaca()) && this.tipoVehiculo.equals(castOther.tipoVehiculo);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.placa.hashCode();
        hash = hash * prime + this.tipoVehiculo.hashCode();

        return hash;
    }
}
