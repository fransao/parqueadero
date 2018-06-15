package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity (name="Vehiculo")
@NamedQuery (name = "Vehiculo.findByPlaca", query = "SELECT vehiculo FROM Vehiculo vehiculo Where vehiculo.placa = :placa")
public class VehiculoEntidad implements Serializable {

    private static final long serialVersionUID = -8420749093917775827L;

    @Id
    private String placa;
    
    @OneToOne
    @JoinColumn(name="tipoVehiculo", referencedColumnName="tipoVehiculo")
    private TipoVehiculoEntidad tipoVehiculo;

    private Integer cilindraje;
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVehiculoEntidad getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculoEntidad tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }
    
}
