package parqueadero.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

    private Util() {
        
    }
    
    public static final int getDiasEntreDosFechas(Date fechaInicial, Date fechaFinal) {
        
        int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);
        
        int dias = 0;
        
        if (diferencia > 86400) {
            dias = diferencia / 86400;
        }
        
        
        
        return dias;
    }
    
    public static final int getHorasEntreDosFechas(Date fechaInicial, Date fechaFinal) {
        
        int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);
        
        int dias = 0;
        int horas = 0;
        
        if (diferencia > 86400) {
            dias = diferencia / 86400;
            diferencia = diferencia - (dias * 86400);
        }
        
        if (diferencia > 3600) {
            horas = diferencia / 3600;
            diferencia = diferencia - (horas * 3600);
        }
        
        if (diferencia > 0) {
            horas += 1;
        }
        
        return horas;
    }
    
    public static Date crearFecha(int dia, int mes, int anio) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anio);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    public static Date crearFechaTiempo(int dia, int mes, int anio, int hora, int minuto, int segundo, int milisegundo) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anio);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);
        calendar.set(Calendar.SECOND, segundo);
        calendar.set(Calendar.MILLISECOND, milisegundo);
        
        return calendar.getTime();
    }
    
    public static boolean isEmpty (String cadena) {
        return cadena == null || cadena.isEmpty();
    }
}
