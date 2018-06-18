package parqueadero.repositorio;

import parqueadero.dominio.Vehiculo;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;

@Deprecated
public interface ICustomRepository {

    GestionVehiculoEntidad estaVehiculoIngresado(Vehiculo vehiculo);
}
