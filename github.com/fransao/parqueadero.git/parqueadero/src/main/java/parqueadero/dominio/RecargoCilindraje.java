package parqueadero.dominio;

public class RecargoCilindraje {

    private int idCilindraje;
    private int cilindrajeDesde;
    private int cilindrajeHasta;
    private float valor;
    
    public int getIdCilindraje() {
        return idCilindraje;
    }
    
    public void setIdCilindraje(int cilindraje) {
        this.idCilindraje = cilindraje;
    }
    
    public int getCilindrajeDesde() {
        return cilindrajeDesde;
    }

    public void setCilindrajeDesde(int cilindrajeDesde) {
        this.cilindrajeDesde = cilindrajeDesde;
    }

    public int getCilindrajeHasta() {
        return cilindrajeHasta;
    }

    public void setCilindrajeHasta(int cilindrajeHasta) {
        this.cilindrajeHasta = cilindrajeHasta;
    }

    public float getValor() {
        return valor;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
