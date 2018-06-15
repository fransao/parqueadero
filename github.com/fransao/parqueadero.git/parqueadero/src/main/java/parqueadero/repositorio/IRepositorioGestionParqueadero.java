package parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;

import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidadPK;

public interface IRepositorioGestionParqueadero extends CrudRepository<GestionVehiculoEntidad, GestionVehiculoEntidadPK> {

    Vehiculo estaVehiculoIngresado(Vehiculo vehiculo);
    
}
