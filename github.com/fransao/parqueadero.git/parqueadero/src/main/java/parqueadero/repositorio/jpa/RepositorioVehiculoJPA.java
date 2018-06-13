package parqueadero.repositorio.jpa;

import parqueadero.dominio.Vehiculo;

public interface RepositorioVehiculoJPA {

    /**
     * Obtiene un vehiculo dado la placa del vehiculo.
     * @param placa
     */
    Vehiculo obtenerVehiculo (String placa);
    
}
