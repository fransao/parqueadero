package parqueadero.servicio;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;

public interface IVigilanteServicio {

    void registrarPlacaVehiculo (Vehiculo vehiculo);
    void registrarIngresoVehiculo (GestionVehiculo gestionVehiculo);
    void registrarSalidaVehiculo(GestionVehiculo gestionVehiculo);
    GestionVehiculo estaVehiculoIngresado(Vehiculo vehiculo);
    void desocuparParqueadero();
}
