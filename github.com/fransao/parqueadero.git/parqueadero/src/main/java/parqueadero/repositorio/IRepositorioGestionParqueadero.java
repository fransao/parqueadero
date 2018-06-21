package parqueadero.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import parqueadero.persistencia.entidad.GestionVehiculoEntidad;

public interface IRepositorioGestionParqueadero extends CrudRepository<GestionVehiculoEntidad, Long> {

    @Query(value = "SELECT gv FROM GestionVehiculo gv WHERE gv.vehiculo.placa = :paramPlaca AND gv.estadoParqueo.estado = :paramEstadoParqueo")
    public GestionVehiculoEntidad estaVehiculoIngresado(@Param("paramPlaca") String paramPlaca, @Param("paramEstadoParqueo") int paramEstadoParqueo);
    
    @Query(value = "SELECT gv FROM GestionVehiculo gv WHERE gv.estadoParqueo.estado = 1 ")
    public List<GestionVehiculoEntidad> obtenerVehiculosEnElParqueadero ();
}
