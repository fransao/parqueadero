package parqueadero.servicio;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;

public interface IVehiculoServicio {

    void registrarTipoVehiculo (TipoVehiculo tipoVehiculo);
    void registrarEstadoParqueo (EstadoParqueo estadoParqueo);
    void registrarPlacaVehiculo (Vehiculo vehiculo);
    
    Vehiculo obtenerVehiculoPorPlaca (String placa);
    TipoVehiculo obtenerTipoVehiculo (EnumTipoVehiculo tipoVehiculo);
}
