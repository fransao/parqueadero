package parqueadero.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="GestionVehiculo")
@NamedQuery (name="GestionVehiculo.obtenerGestionVehiculo", query="SELECT gestionVehiculo FROM GestionVehiculo gestionVehiculo WHERE gestionVehiculo = :gestionhehiculo")
public class GestionVehiculoEntidad implements Serializable {

    private static final long serialVersionUID = -6788333527455308654L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long secuenciaParqueoVehiculo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehiculo")
    private VehiculoEntidad vehiculo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaIngreso")
    private java.util.Date fechaIngreso;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaSalida")
    private java.util.Date fechaSalida;
    
    @OneToOne
    @JoinColumn(name="estadoParqueo", referencedColumnName="estado")
    private EstadoParqueoEntidad estadoParqueo;
    
    private Float valor;

    public java.util.Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(java.util.Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public EstadoParqueoEntidad getEstadoParqueo() {
        return estadoParqueo;
    }

    public void setEstadoParqueo(EstadoParqueoEntidad estadoParqueo) {
        this.estadoParqueo = estadoParqueo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }

    public java.util.Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(java.util.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
}
