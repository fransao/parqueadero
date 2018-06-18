package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity (name="Recargo")
@NamedQuery (name="Recargo.obtenerRecargo", query="SELECT recargo FROM Recargo recargo WHERE recargo.cilindraje = :cilindraje")
public class RecargoCilindrajeEntidad implements Serializable {

    private static final long serialVersionUID = 6682843763585086568L;

    @Id
    private Integer idCilindraje;
    @Id
    private Integer tipoVehiculo;
    @Id
    private Integer cilindrajeDesde;
    @Id
    private Integer cilindrajeHasta;
    
    @Column (nullable=false)
    private Float   valor;

    public Integer getIdCilindraje() {
        return idCilindraje;
    }

    public void setIdCilindraje(Integer cilindraje) {
        this.idCilindraje = cilindraje;
    }

    public Float getValor() {
        return valor;
    }

    public Integer getItemCilindraje() {
        return idCilindraje;
    }

    public void setItemCilindraje(Integer itemCilindraje) {
        this.idCilindraje = itemCilindraje;
    }

    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getCilindrajeDesde() {
        return cilindrajeDesde;
    }

    public void setCilindrajeDesde(Integer cilindrajeDesde) {
        this.cilindrajeDesde = cilindrajeDesde;
    }

    public Integer getCilindrajeHasta() {
        return cilindrajeHasta;
    }

    public void setCilindrajeHasta(Integer cilindrajeHasta) {
        this.cilindrajeHasta = cilindrajeHasta;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
