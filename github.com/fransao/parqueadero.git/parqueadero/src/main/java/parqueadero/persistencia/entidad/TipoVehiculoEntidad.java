package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity (name="TipoVehiculo")
@NamedQuery (name="TipoVehiculo.obtenerTipoVehiculo", query="SELECT tipovehiculo FROM TipoVehiculo tipovehiculo WHERE tipovehiculo.tipoVehiculo = :tipovehiculo")
public class TipoVehiculoEntidad implements Serializable {

    private static final long serialVersionUID = -1012108427175404782L;

    @Id
    private Integer tipoVehiculo;
    
    @Column(nullable = false)
    private String nombreTipoVehiculo;

    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }
    
}
