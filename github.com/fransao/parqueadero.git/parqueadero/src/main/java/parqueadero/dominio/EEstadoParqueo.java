package parqueadero.dominio;

import parqueadero.enumerado.EnumEstadoParqueo;

public class EEstadoParqueo {

    private EnumEstadoParqueo estado;
    private String nombreEstadoParqueo;
    
    public EEstadoParqueo() {
        
    }
    
    public EEstadoParqueo (EnumEstadoParqueo estadoParqueo, String nombreEstadoParqueo) {
        this.estado = estadoParqueo;
        this.nombreEstadoParqueo = nombreEstadoParqueo;
    }
    
    public EnumEstadoParqueo getEstadoParqueo() {
        return estado;
    }
    
    public void setEstadoParqueo(EnumEstadoParqueo estadoParqueo) {
        this.estado = estadoParqueo;
    }
    
    public String getNombreEstadoParqueo() {
        return nombreEstadoParqueo;
    }
    public void setNombreEstadoParqueo(String nombreEstadoParqueo) {
        this.nombreEstadoParqueo = nombreEstadoParqueo;
    }
    
    
}
