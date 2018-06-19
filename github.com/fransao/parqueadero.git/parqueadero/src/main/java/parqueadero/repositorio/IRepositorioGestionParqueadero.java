package parqueadero.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidadPK;

public interface IRepositorioGestionParqueadero extends CrudRepository<GestionVehiculoEntidad, GestionVehiculoEntidadPK> {

    @Query("SELECT gv FROM GestionVehiculo gv WHERE gv.vehiculo.placa = :paramPlaca AND " +
           " gv.estadoParqueo.estado = :paramEstadoParqueo ")
    public GestionVehiculoEntidad estaVehiculoIngresado(@Param("paramPlaca") String paramPlaca, @Param("paramEstadoParqueo") int paramEstadoParqueo);
}
