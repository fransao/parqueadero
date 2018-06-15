package parqueadero.servicio;

import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;

public interface IGestionVehiculoServicio {

    void ingresarVehiculo (GestionVehiculo gestionVehiculo);
    
    Vehiculo estaVehiculoIngresado(Vehiculo vehiculo);
}
