package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.UnidadTiempoEntidad;

public interface IReposotorioUnidadTiempo extends CrudRepository<UnidadTiempoEntidad, Integer> {

}
