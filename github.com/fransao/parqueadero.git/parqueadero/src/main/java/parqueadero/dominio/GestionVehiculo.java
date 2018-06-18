package parqueadero.dominio;

import java.util.Date;

import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTipoVehiculo;

public class GestionVehiculo {

    private EnumTipoVehiculo enumTipoVehiculo;
    private TipoVehiculo tipoVehiculo;
    private Vehiculo vehiculo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private float valor;
    private EnumEstadoParqueo estadoParqueo;
    
    public GestionVehiculo () {
        
    }
    
    public GestionVehiculo(EnumTipoVehiculo tipoVehiculo, Vehiculo vehiculo, Date fechaIngreso) {
        this.enumTipoVehiculo = tipoVehiculo;
        this.vehiculo     = vehiculo;
        this.fechaIngreso = fechaIngreso;
    }

    public EnumTipoVehiculo getEnumTipoVehiculo() {
        return enumTipoVehiculo;
    }

    public void setEnumTipoVehiculo(EnumTipoVehiculo enumTipoVehiculo) {
        this.enumTipoVehiculo = enumTipoVehiculo;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public EnumEstadoParqueo getEstadoParqueo() {
        return estadoParqueo;
    }

    public void setEstadoParqueo(EnumEstadoParqueo estadoParqueo) {
        this.estadoParqueo = estadoParqueo;
    }

}
