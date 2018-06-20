package parqueadero.dominio;

import parqueadero.enumerado.EnumTipoVehiculo;

public class Vehiculo {

    private String placa;
    private EnumTipoVehiculo tipoVehiculo;
    
    public Vehiculo () {
        
    }
    
    public Vehiculo (String placa) {
        this.placa = placa;
    }
    
    public Vehiculo (String placa, EnumTipoVehiculo tipoVehiculo) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public EnumTipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
