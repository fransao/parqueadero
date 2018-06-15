package testdatabuilder;

import parqueadero.dominio.TipoVehiculo;

public class TipoVehiculoDataBuilder {

    private static final int TIPO_VEHICULO = 1;
    private static final String NOMBRE_TIPO_VEHICULO = "MOTO";
    
    private int tipoVehiculo;
    private String nombreTipoVehiculo;
    
    public TipoVehiculoDataBuilder (){
        this.tipoVehiculo = TIPO_VEHICULO;
        this.nombreTipoVehiculo = NOMBRE_TIPO_VEHICULO;
    }
    
    public TipoVehiculoDataBuilder(int tipoVehiculo, String nombreTipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public TipoVehiculoDataBuilder conTipoVehiculo(int tipoVehiculo) {
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
