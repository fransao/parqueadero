package parqueadero.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;

@Repository
@Transactional
@Deprecated
public class CustomRepository implements ICustomRepository {

    private static final String SQL_OBTENER_VEHICULO = "SELECT gv " +
                                                       "FROM GestionVehiculoEntidad gv " +
                                                       "WHERE gv.gestionVehiculoEntidadPK.placa = :paramPlaca AND " +
                                                       " gv.gestionVehiculoEntidadPK.tipoVehiculo = :paramTipoVehiculo AND " +
                                                       " gv.estadoParqueo.estadoParqueo.estado = :paramEstadoParqueo ;";
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    @Deprecated
    public GestionVehiculoEntidad estaVehiculoIngresado(Vehiculo vehiculo) {
        Query query = entityManager.createNativeQuery(SQL_OBTENER_VEHICULO, GestionVehiculoEntidad.class);
        query.setParameter("paramPlaca",vehiculo.getPlaca());
        query.setParameter("paramTipoVehiculo", vehiculo.getTipoVehiculo().getTipoVehiculo());
        query.setParameter("paramEstadoParqueo", EnumEstadoParqueo.INGRESADO.getEstadoParqueo());
        
        return (GestionVehiculoEntidad)query.getSingleResult();
    }

}
