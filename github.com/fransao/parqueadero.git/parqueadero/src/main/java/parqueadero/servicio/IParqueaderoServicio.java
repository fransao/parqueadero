package parqueadero.servicio;

import java.util.List;

import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;

public interface IParqueaderoServicio {

    List<TarifaXTipoVehiculo> obtenerTarifasXTipoVehiculo();
    List<RecargoCilindraje> obtenerRecargos();
    
}
