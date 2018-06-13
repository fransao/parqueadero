package parqueadero.dominio;

import java.util.Date;

import parqueadero.enumerado.EnumTipoVehiculo;

public class IngresoVehiculo {

    private EnumTipoVehiculo tipoVehiculo;
    private Vehiculo vehiculo;
    private Date fechaIngreso;
    
    public IngresoVehiculo(EnumTipoVehiculo tipoVehiculo, Vehiculo vehiculo, Date fechaIngreso) {
        this.tipoVehiculo = tipoVehiculo;
        this.vehiculo     = vehiculo;
        this.fechaIngreso = fechaIngreso;
    }

    public EnumTipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
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

}
