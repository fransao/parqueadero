package parqueadero.dominio;

public class TipoVehiculo {

    private int codTipoVehiculo;
    private String nombreTipoVehiculo;
    
    public TipoVehiculo(int tipoVehiculo, String nombreTipoVehiculo) {
        this.codTipoVehiculo = tipoVehiculo;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }
    
    public int getCodTipoVehiculo() {
        return codTipoVehiculo;
    }

    public void setCodTipoVehiculo(int codTipoVehiculo) {
        this.codTipoVehiculo = codTipoVehiculo;
    }
    
    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }
    
    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

}
