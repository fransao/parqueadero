package parqueadero.dominio;

public class Carro extends Vehiculo {

    public Carro(Vehiculo vehiculo) {
        super(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
    }

}
