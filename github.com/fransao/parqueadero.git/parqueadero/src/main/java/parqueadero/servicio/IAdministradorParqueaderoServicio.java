package parqueadero.servicio;

import java.util.List;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTipoVehiculo;

public interface IAdministradorParqueaderoServicio {

    List<TarifaXTipoVehiculo> obtenerTarifasXTipoVehiculo();
    List<RecargoCilindraje> obtenerRecargos();
    RecargoCilindraje obtenerRecargo(Vehiculo vehiculo);
    void registrarRecargo(RecargoCilindraje recargoCilindraje);
    void registrarTarifas(List<TarifaXTipoVehiculo> listTarifaVehiculo);
    List<GestionVehiculo> obtenerVehiculosEnElParqueadero();
    
    void registrarTipoVehiculo (TipoVehiculo tipoVehiculo);
    void registrarEstadoParqueo (EstadoParqueo estadoParqueo);
    TipoVehiculo obtenerTipoVehiculo (EnumTipoVehiculo tipoVehiculo);
    
    Vehiculo obtenerVehiculoPorPlaca (String placa);
    
    EstadoParqueo obtenerEstadoParqueo(EstadoParqueo estadoParqueo);
}