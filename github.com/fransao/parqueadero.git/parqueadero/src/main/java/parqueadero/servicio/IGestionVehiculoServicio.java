package parqueadero.servicio;

import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;

public interface IGestionVehiculoServicio {

    void registrarIngresoVehiculo (GestionVehiculo gestionVehiculo);
    void registrarSalidaVehiculo(GestionVehiculo gestionVehiculo);
    EstadoParqueo obtenerEstadoParqueo(EstadoParqueo estadoParqueo);
    GestionVehiculoEntidad estaVehiculoIngresado(Vehiculo vehiculo);
}
