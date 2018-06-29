package parqueadero.testdatabuilder;

import parqueadero.dominio.EEstadoParqueo;
import parqueadero.enumerado.EnumEstadoParqueo;

public class EstadoParqueoDataBuilder {

    private static final EnumEstadoParqueo ESTADO_PARQUEO = EnumEstadoParqueo.INGRESADO;
    private static final String NOMBRE_ESTADO_PARQUEO     = "Ingresado";
    
    private EnumEstadoParqueo codEstadoParqueo;
    private String nombreEstadoParqueo;
    
    public EstadoParqueoDataBuilder() {
        super();
        this.codEstadoParqueo = ESTADO_PARQUEO;
        this.nombreEstadoParqueo = NOMBRE_ESTADO_PARQUEO;
    }
    
    public EstadoParqueoDataBuilder conCodEstadoParqueo(EnumEstadoParqueo enumEstadoParqueo) {
        this.codEstadoParqueo = enumEstadoParqueo;
        return this;
    }
    
    public EstadoParqueoDataBuilder conNombreEstadoParqueo (String nombreEstadoParqueo) {
        this.nombreEstadoParqueo = nombreEstadoParqueo;
        return this;
    }
    
    public EEstadoParqueo build() {
        return new EEstadoParqueo(codEstadoParqueo, nombreEstadoParqueo);
    }
}
