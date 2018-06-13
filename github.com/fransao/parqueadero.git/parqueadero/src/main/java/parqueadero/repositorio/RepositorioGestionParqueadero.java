package parqueadero.repositorio;

import parqueadero.dominio.Vehiculo;

public interface RepositorioGestionParqueadero {

    void ingresarVehiculo();
    
    Vehiculo obtenerVehiculo(String placa);
    
}
