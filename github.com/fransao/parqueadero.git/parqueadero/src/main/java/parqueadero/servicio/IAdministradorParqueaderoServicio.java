package parqueadero.servicio;

import java.util.List;

import parqueadero.dominio.EEstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;

public interface IAdministradorParqueaderoServicio {

    List<TarifaXTipoVehiculo> obtenerTarifasXTipoVehiculo();
    List<RecargoCilindraje> obtenerRecargos();
    RecargoCilindraje obtenerRecargo(Vehiculo vehiculo);
    void registrarRecargo(RecargoCilindraje recargoCilindraje);
    void registrarUnidadTiempo (List<EnumTiempo> listTiempo);
    void registrarTarifas(List<TarifaXTipoVehiculo> listTarifaVehiculo);
    List<GestionVehiculo> obtenerVehiculosEnElParqueadero();
    
    void registrarTipoVehiculo (TipoVehiculo tipoVehiculo);
    void registrarEstadoParqueo (EEstadoParqueo eEstadoParqueo);
    TipoVehiculo obtenerTipoVehiculo (EnumTipoVehiculo tipoVehiculo);
    
    Vehiculo obtenerVehiculoPorPlaca (String placa);
    
    EEstadoParqueo obtenerEstadoParqueo(EEstadoParqueo eEstadoParqueo);
}
