package parqueadero.dominio;

import java.util.Calendar;
import java.util.Date;

import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.servicio.IGestionVehiculoServicio;
import parqueadero.servicio.IParqueaderoServicio;
import parqueadero.util.Util;

public class Vigilante {

    private static final long MILLSECS_PER_DAY             = 24 * 60 * 60 * 1000; //Milisegundos al día
    private static final String MSJ_PARQUEADERO_VEHICULO   = "El parqueadero solo permite ingresar Carros y Motos";
    public static final  String MSJ_VEHICULO_NO_AUTORIZADO = "El vehiculo no esta autorizado para ingresar (puede ingresar el domingo o lunes)";
    
    private IGestionVehiculoServicio gestionVehiculoServicio;
    private IParqueaderoServicio parqueaderoServicio;
    
    public Vigilante (IGestionVehiculoServicio gestionVehiculoServicio, IParqueaderoServicio parqueaderoServicio) {
        this.gestionVehiculoServicio = gestionVehiculoServicio;
        this.parqueaderoServicio = parqueaderoServicio;
    }
    
    public void ingresarVehiculoAParqueadero(Vehiculo vehiculo, Date fechaIngreso) {
        
        if (placaIniciaA(vehiculo.getPlaca())) {
            validarDiaDomingoLunes(fechaIngreso);
        }
        
        if (vehiculo instanceof Moto || vehiculo instanceof Carro) {
            ingresarVehiculo(vehiculo, fechaIngreso);
        } else {
            throw new ParqueaderoException(MSJ_PARQUEADERO_VEHICULO);
        }
        
    }

    public float generarCobroVechiculoParqueo(GestionVehiculo gestionVehiculo) {
        
        float subtotal = 0.0f;
        float recargo  = 0.0f;
        float totalAPagar = 0.0f;
        
        if (gestionVehiculo == null) {
            return subtotal;
        }
        
        Vehiculo vehiculo = gestionVehiculo.getVehiculo();
        
        subtotal = calcularValorParqueadero(vehiculo, gestionVehiculo.getFechaIngreso(), gestionVehiculo.getFechaSalida());
        
        recargo = calcularRecaudo(vehiculo);
        
        totalAPagar = subtotal + recargo;
        
        
        
        return subtotal;
    }
    
    private float calcularRecaudo(Vehiculo vehiculo) {
        float recargo = 0.0f;
        RecargoCilindraje recargoCilindraje = parqueaderoServicio.obtenerRecargo(vehiculo);
        if (recargoCilindraje != null) {
            recargo = recargoCilindraje.getValor();
        }
        
        return recargo;
    }

    private float calcularValorParqueadero (Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida) {
        float totalAPagar = 0.0f;
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, fechaSalida);
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, fechaSalida);
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        if (vehiculo instanceof Moto) {
            calcularRecargo(vehiculo);
        }
        
        return totalAPagar;
    }
    
    private void calcularRecargo(Vehiculo vehiculo) {
        // TODO Auto-generated method stub
        
    }

    private void validarDiaDomingoLunes(Date fechaIngreso) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaIngreso);
        
        int diasemana = calendario.get(Calendar.DAY_OF_WEEK);
        
        if (Calendar.SUNDAY != diasemana && diasemana != Calendar.MONDAY) {
            throw new ParqueaderoException(MSJ_VEHICULO_NO_AUTORIZADO);
        }
        
    }

    private boolean placaIniciaA(String placa) {
        return placa.startsWith("A") || placa.startsWith("a");
    }

    private void ingresarVehiculo (Vehiculo vehiculo, Date fechaIngreso) {
        GestionVehiculo ingresoVehiculo = new GestionVehiculo(vehiculo.getTipoVehiculo(), vehiculo, fechaIngreso);
        ingresoVehiculo.setEstadoParqueo(EnumEstadoParqueo.INGRESADO);
        gestionVehiculoServicio.ingresarVehiculo(ingresoVehiculo);
    }
    
    public GestionVehiculo obtenerVehiculoIngresado(Vehiculo vehiculo) {
        return VehiculoBuilder.convertirGestionVehiculoADominio(gestionVehiculoServicio.estaVehiculoIngresado(vehiculo));
    }
    
    public boolean estaVehiculoIngresado(Vehiculo vehiculo) {
        return gestionVehiculoServicio.estaVehiculoIngresado(vehiculo) != null;
    }
    
}
