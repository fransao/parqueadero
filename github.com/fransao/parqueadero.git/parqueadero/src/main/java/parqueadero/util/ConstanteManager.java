package parqueadero.util;

import parqueadero.dominio.Parqueadero;

public class ConstanteManager {

    public static final String MSJ_PARQUEADERO_VEHICULO   = "El parqueadero solo permite ingresar Carros y Motos";
    public static final String MSJ_VEHICULO_NO_AUTORIZADO = "El vehiculo no esta autorizado para ingresar (puede ingresar el domingo o lunes)";
    
    public static final String MSJ_MAXIMO_CARROS_PARQUEADOS   = "El parqueadero solo puede tener máximo " + Parqueadero.CANTIDAD_MAXIMA_CARROS + " carros";
    public static final String MSJ_MAXIMO_MOTOS_PARQUEADOOS   = "El parqueadero solo puede tener máximo " + Parqueadero.CANTIDAD_MAXIMA_MOTOS + " motos";
    public static final String MSJ_VEHICULO_YA_ESTA_INGRESADO = "Ya hay un vehiculo ingresado con esa placa";
    public static final String MSJ_VEHICULO_NO_ESTA_INGRESADO = "El vehiculo no esta ingresado";
    
    private ConstanteManager () {
        
    }

}
