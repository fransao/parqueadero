package parqueadero.repositorio;

import parqueadero.dominio.Vehiculo;

public interface ICustomRepository {

    Vehiculo estaVehiculoIngresado(Vehiculo vehiculo);
}
