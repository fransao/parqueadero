package parqueadero.persistencia.sistema;

import javax.persistence.EntityManager;

import parqueadero.peristencia.RepositorioGestionParqueaderoPersistente;
import parqueadero.peristencia.RepositorioParqueaderoPersistente;
import parqueadero.persistencia.conexion.ConexionJPA;
import parqueadero.repositorio.RepositorioGestionParqueadero;
import parqueadero.repositorio.RepositorioVehiculo;

public class SistemaDePersistencia {

    private EntityManager entityManager;

    public SistemaDePersistencia() {
        this.entityManager = new ConexionJPA().createEntityManager();
    }

    public RepositorioVehiculo obtenerRepositorioVehiculo() {
        return new RepositorioParqueaderoPersistente(entityManager);
    }

    public RepositorioGestionParqueadero obtenerRepositorioGestionparqueadero() {
        return new RepositorioGestionParqueaderoPersistente(entityManager, obtenerRepositorioVehiculo());
    }
    public void iniciar() {
        entityManager.getTransaction().begin();
    }

    public void terminar() {
        entityManager.getTransaction().commit();
    }
    
}
