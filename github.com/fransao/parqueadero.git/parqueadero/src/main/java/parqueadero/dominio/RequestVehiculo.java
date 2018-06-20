package parqueadero.dominio;

import parqueadero.enumerado.EnumTipoVehiculo;

public class RequestVehiculo extends Vehiculo {
    
    private int cilindraje;

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public Vehiculo getVehiculo () {
        return new Vehiculo(getPlaca(), EnumTipoVehiculo.getEnumTipoVehiculo(getCilindraje()));
    }
    
    public Moto getMoto () {
        return new Moto(getVehiculo(), getCilindraje());
    }
    
    public Vehiculo getCarro () {
        return new Carro(getVehiculo());
    }
}
