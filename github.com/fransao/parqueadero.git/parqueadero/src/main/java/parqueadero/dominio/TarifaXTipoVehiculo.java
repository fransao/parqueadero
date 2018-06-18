package parqueadero.dominio;

import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;

public class TarifaXTipoVehiculo {

    private EnumTipoVehiculo tipoVehiculo;
    private EnumTiempo unidadTiempo;
    private float valor;
    
    public EnumTipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
    
    public void setTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public EnumTiempo getUnidadTiempo() {
        return unidadTiempo;
    }
    
    public void setUnidadTiempo(EnumTiempo unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }
    
    public float getValor() {
        return valor;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
