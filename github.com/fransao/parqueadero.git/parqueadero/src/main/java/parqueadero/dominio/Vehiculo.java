package parqueadero.dominio;

public class Vehiculo {

    String placa;
    
    public Vehiculo (String placa) {
        this.placa = placa;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
}