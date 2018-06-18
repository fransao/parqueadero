package parqueadero.dominio;

import java.util.Date;

public interface ICobro {

    float calcularCobro(Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida);
}
