package parqueadero.servicio;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;

public interface IVigilanteServicio {

    void registrarPlacaVehiculo (Vehiculo vehiculo);
    GestionVehiculo registrarIngresoVehiculo (GestionVehiculo gestionVehiculo);
    GestionVehiculo registrarSalidaVehiculo(GestionVehiculo gestionVehiculo);
    GestionVehiculo estaVehiculoIngresado(Vehiculo vehiculo);
    void desocuparParqueadero();
}
