package parqueadero.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;
import parqueadero.util.Util;

public class Vigilante {

    private static final String MSJ_PARQUEADERO_VEHICULO   = "El parqueadero solo permite ingresar Carros y Motos";
    public static final  String MSJ_VEHICULO_NO_AUTORIZADO = "El vehiculo no esta autorizado para ingresar (puede ingresar el domingo o lunes)";
    
    private IVigilanteServicio vigilanteServicio;
    private IAdministradorParqueaderoServicio administradorParqueaderoServicio;
    
    public Vigilante (IVigilanteServicio vigilanteServicio, IAdministradorParqueaderoServicio administradorParqueaderoServicio) {
        this.vigilanteServicio = vigilanteServicio;
        this.administradorParqueaderoServicio = administradorParqueaderoServicio;
    }
    
    public void registrarIngresoVehiculoAParqueadero(Vehiculo vehiculo, Date fechaIngreso) {
        
        if (placaIniciaA(vehiculo.getPlaca())) {
            validarDiaDomingoLunes(fechaIngreso);
        }
        
        if (vehiculo instanceof Moto || vehiculo instanceof Carro) {
            ingresarVehiculo(vehiculo, fechaIngreso);
        } else {
            throw new ParqueaderoException(MSJ_PARQUEADERO_VEHICULO);
        }
        
    }

    public void registrarSalidaVehiculoParqueadero(Vehiculo vehiculo, Date fechaSalida) {
        
        GestionVehiculo salidaVehiculo = vigilanteServicio.estaVehiculoIngresado(vehiculo);
        salidaVehiculo.setEstadoParqueo(EnumEstadoParqueo.SALIDA);
        salidaVehiculo.setFechaSalida(fechaSalida);
        vigilanteServicio.registrarIngresoVehiculo(salidaVehiculo);
    }
    
    public float generarCobroVechiculoParqueo(GestionVehiculo gestionVehiculo) {
        
        float totalAPagar = 0.0f;
        
        if (gestionVehiculo == null) {
            return totalAPagar;
        }
        
        totalAPagar = calcularValorParqueadero(gestionVehiculo.getVehiculo(), gestionVehiculo.getFechaIngreso(), gestionVehiculo.getFechaSalida());
        
        return totalAPagar;
    }
    
    private float calcularValorParqueadero (Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida) {
        float totalAPagar = 0.0f;
        
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        RecargoCilindraje recargoVehiculo = administradorParqueaderoServicio.obtenerRecargo(vehiculo);
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, fechaSalida);
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, fechaSalida);
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        if (diasEntreDosFechas > 0) {
            Optional<TarifaXTipoVehiculo> optTarifaDia = listTarifa.stream().
                    filter(t -> t.getTipoVehiculo().equals(vehiculo.getTipoVehiculo()) && EnumTiempo.DIA.equals(t.getUnidadTiempo())).
                    findFirst();
            if (optTarifaDia.isPresent()) {
                totalAPagar = optTarifaDia.get().getValor() * diasEntreDosFechas; 
            }
        }
        
        if (horasEntreDosFechas > 0) {
            Optional<TarifaXTipoVehiculo> optTarifaHora = listTarifa.stream().
                    filter(t -> t.getTipoVehiculo().equals(vehiculo.getTipoVehiculo()) && EnumTiempo.HORA.equals(t.getUnidadTiempo())).
                    findFirst();
            if (optTarifaHora.isPresent()) {
                totalAPagar += optTarifaHora.get().getValor() * horasEntreDosFechas; 
            }
        }
        
        if (recargoVehiculo != null) {
            totalAPagar +=  recargoVehiculo.getValor();
        }
        
        
        return totalAPagar;
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
        GestionVehiculo ingresoVehiculo = new GestionVehiculo(vehiculo, fechaIngreso);
        ingresoVehiculo.setEstadoParqueo(EnumEstadoParqueo.INGRESADO);
        ingresoVehiculo.setFechaIngreso(fechaIngreso);
        vigilanteServicio.registrarIngresoVehiculo(ingresoVehiculo);
    }
    
    public GestionVehiculo obtenerVehiculoIngresado(Vehiculo vehiculo) {
        return vigilanteServicio.estaVehiculoIngresado(vehiculo);
    }
    
    public boolean estaVehiculoIngresado(Vehiculo vehiculo) {
        return vigilanteServicio.estaVehiculoIngresado(vehiculo) != null;
    }
    
}
