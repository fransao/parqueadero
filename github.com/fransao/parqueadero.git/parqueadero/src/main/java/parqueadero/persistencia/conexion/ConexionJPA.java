package parqueadero.persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {

    private static final String PARQUEADERO_PU_TEST = "parqueadero-pu-test";
    private static EntityManagerFactory entityManagerFactory;

    public ConexionJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PARQUEADERO_PU_TEST);
    }
    
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    
}
