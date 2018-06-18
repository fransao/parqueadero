package testdatabuilder;

import parqueadero.dominio.TipoVehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;

public class TipoVehiculoDataBuilder {

    private static final EnumTipoVehiculo TIPO_VEHICULO = EnumTipoVehiculo.MOTO;
    private static final String NOMBRE_TIPO_VEHICULO = "MOTO";
    
    private EnumTipoVehiculo tipoVehiculo;
    private String nombreTipoVehiculo;
    
    public TipoVehiculoDataBuilder (){
        this.tipoVehiculo = TIPO_VEHICULO;
        this.nombreTipoVehiculo = NOMBRE_TIPO_VEHICULO;
    }
    
    public TipoVehiculoDataBuilder(EnumTipoVehiculo tipoVehiculo, String nombreTipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public TipoVehiculoDataBuilder conTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }
    
    public TipoVehiculoDataBuilder conNombre(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
        return this;
    }
    
    public TipoVehiculo build() {
        return new TipoVehiculo (tipoVehiculo, nombreTipoVehiculo);
    }
}
