package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.VehiculoEntidad;

public interface IRepositorioVehiculo extends CrudRepository<VehiculoEntidad, String> {

}
