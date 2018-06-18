package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.EstadoParqueoEntidad;

public interface IRepositorioEstadoParqueo extends CrudRepository<EstadoParqueoEntidad, Integer> {

}
