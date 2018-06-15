package parqueadero.dominio;

public class Moto extends Vehiculo {

    private int cilindraje;
    
    public Moto(Vehiculo vehiculo, int cilindraje) {
        super(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
        this.cilindraje = cilindraje;
    }
    
    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

}
