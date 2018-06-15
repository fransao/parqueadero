package parqueadero.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import parqueadero.dominio.Vehiculo;

@Repository
@Transactional
public class CustomRepository implements ICustomRepository {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public Vehiculo estaVehiculoIngresado(Vehiculo vehiculo) {
        Query query = entityManager.createNativeQuery("SELECT gestionVehiculo FROM GestionVehiculo gestionVehiculo WHERE gestionVehiculoas  " +
                "WHERE em.firstname LIKE ?", Vehiculo.class);
        query.setParameter(1, vehiculo.getPlaca() + "%");
        
        return (Vehiculo)query.getSingleResult();
    }

}
