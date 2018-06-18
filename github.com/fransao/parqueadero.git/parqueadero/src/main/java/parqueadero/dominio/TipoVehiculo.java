package parqueadero.dominio;

import parqueadero.enumerado.EnumTipoVehiculo;

public class TipoVehiculo {

    private EnumTipoVehiculo codTipoVehiculo;
    private String nombreTipoVehiculo;
    
    public TipoVehiculo(EnumTipoVehiculo tipoVehiculo, String nombreTipoVehiculo) {
        this.codTipoVehiculo = tipoVehiculo;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }
    
    public EnumTipoVehiculo getCodTipoVehiculo() {
        return codTipoVehiculo;
    }

    public void setCodTipoVehiculo(EnumTipoVehiculo codTipoVehiculo) {
        this.codTipoVehiculo = codTipoVehiculo;
    }
    
    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }
    
    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

}
