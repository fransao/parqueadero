package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.TipoVehiculoEntidad;

public interface IRepositorioTipoVehiculo extends CrudRepository<TipoVehiculoEntidad, Integer> {

}
