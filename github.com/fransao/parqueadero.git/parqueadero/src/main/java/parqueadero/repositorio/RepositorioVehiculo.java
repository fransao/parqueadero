package parqueadero.repositorio;

import parqueadero.dominio.Vehiculo;

public interface RepositorioVehiculo {

    /**
     * Permite ingresar un vehiculo al parqueadero.
     */
    void registrarPlacaVehiculo(Vehiculo vehiculo);
    
}
