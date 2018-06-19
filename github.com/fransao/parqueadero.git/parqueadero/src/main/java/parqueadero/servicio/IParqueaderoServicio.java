package parqueadero.servicio;

import java.util.List;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.Vehiculo;

public interface IParqueaderoServicio {

    List<TarifaXTipoVehiculo> obtenerTarifasXTipoVehiculo();
    List<RecargoCilindraje> obtenerRecargos();
    RecargoCilindraje obtenerRecargo(Vehiculo vehiculo);
    void registrarRecargo(RecargoCilindraje recargoCilindraje);
    void registrarTarifas(List<TarifaXTipoVehiculo> listTarifaVehiculo);
    List<GestionVehiculo> obtenerVehiculosEnElParqueadero();
}
