package parqueadero.peristencia;

import javax.persistence.EntityManager;

import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.repositorio.RepositorioGestionParqueadero;
import parqueadero.repositorio.RepositorioVehiculo;
import parqueadero.repositorio.jpa.RepositorioVehiculoJPA;

public class RepositorioGestionParqueaderoPersistente implements RepositorioGestionParqueadero {

    private EntityManager entityManager;
    private RepositorioVehiculoJPA repositorioVehiculoJPA;
    
    public RepositorioGestionParqueaderoPersistente (EntityManager entityManager, RepositorioVehiculo repositorioVehiculo) {
        this.entityManager = entityManager;
        this.repositorioVehiculoJPA = (RepositorioVehiculoJPA) repositorioVehiculo;
    }
    
    @Override
    public void ingresarVehiculo() {
        GestionVehiculoEntidad gestionVehiculoEntidad;
        
    }

    @Override
    public Vehiculo obtenerVehiculo(String placa) {
        // TODO Auto-generated method stub
        return null;
    }

}
