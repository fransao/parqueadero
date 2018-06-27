package parqueadero.dominio;

import java.util.Date;

import parqueadero.enumerado.EnumEstadoParqueo;

public class GestionVehiculo {

    private long secuenciaParqueoVehiculo;
    private Vehiculo vehiculo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private float valor;
    private EnumEstadoParqueo estadoParqueo;
    
    public GestionVehiculo () {
        
    }
    
    public GestionVehiculo(Vehiculo vehiculo, Date fechaIngreso) {
        this.vehiculo = vehiculo;;
        this.fechaIngreso = fechaIngreso;
    }

    public long getSecuenciaParqueoVehiculo() {
        return secuenciaParqueoVehiculo;
    }

    public void setSecuenciaParqueoVehiculo(long secuenciaParqueoVehiculo) {
        this.secuenciaParqueoVehiculo = secuenciaParqueoVehiculo;
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
