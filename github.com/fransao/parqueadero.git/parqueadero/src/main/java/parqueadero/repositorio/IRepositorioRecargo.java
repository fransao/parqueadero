package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;

public interface IRepositorioRecargo extends CrudRepository<RecargoCilindrajeEntidad, Integer> {

}
