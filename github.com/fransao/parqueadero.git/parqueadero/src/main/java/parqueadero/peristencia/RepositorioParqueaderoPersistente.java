package parqueadero.peristencia;

import javax.persistence.EntityManager;

import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.repositorio.RepositorioVehiculo;
import parqueadero.repositorio.jpa.RepositorioVehiculoJPA;

public class RepositorioParqueaderoPersistente implements RepositorioVehiculo, RepositorioVehiculoJPA {

    private EntityManager entityManager;
    
    public RepositorioParqueaderoPersistente (EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void registrarPlacaVehiculo(Vehiculo vehiculo) {
        GestionVehiculoEntidad gestionVehiculoEntidad;
        
    }

    @Override
    public Vehiculo obtenerVehiculo(String placa) {
        // TODO Auto-generated method stub
        return null;
    }

}
