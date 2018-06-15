package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity (name="Recargo")
@NamedQuery (name="Recargo.obtenerRecargo", query="SELECT recargo FROM Recargo recargo WHERE recargo.cilindraje = :cilindraje")
public class RecargoEntidad implements Serializable {

    private static final long serialVersionUID = 6682843763585086568L;

    @Id
    private Integer cilindraje;
    
    @Column (nullable=false)
    private Float   valor;

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
