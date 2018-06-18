package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidadPK;

public interface IRepositorioTarifa extends CrudRepository<TarifaXTipoVehiculoEntidad, TarifaXTipoVehiculoEntidadPK> {

}
