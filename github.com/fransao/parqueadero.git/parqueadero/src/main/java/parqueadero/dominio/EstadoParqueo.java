package parqueadero.dominio;

import parqueadero.enumerado.EnumEstadoParqueo;

public class EstadoParqueo {

    private EnumEstadoParqueo estadoParqueo;
    private String nombreEstadoParqueo;
    
    public EstadoParqueo() {
        
    }
    
    public EstadoParqueo (EnumEstadoParqueo estadoParqueo, String nombreEstadoParqueo) {
        this.estadoParqueo = estadoParqueo;
        this.nombreEstadoParqueo = nombreEstadoParqueo;
    }
    
    public EnumEstadoParqueo getEstadoParqueo() {
        return estadoParqueo;
    }
    
    public void setEstadoParqueo(EnumEstadoParqueo estadoParqueo) {
        this.estadoParqueo = estadoParqueo;
    }
    
    public String getNombreEstadoParqueo() {
        return nombreEstadoParqueo;
    }
    public void setNombreEstadoParqueo(String nombreEstadoParqueo) {
        this.nombreEstadoParqueo = nombreEstadoParqueo;
    }
    
    
}
