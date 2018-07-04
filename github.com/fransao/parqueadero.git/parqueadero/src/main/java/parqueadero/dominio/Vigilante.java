package parqueadero.dominio;

import static parqueadero.util.ConstanteManager.MSJ_MAXIMO_CARROS_PARQUEADOS;
import static parqueadero.util.ConstanteManager.MSJ_MAXIMO_MOTOS_PARQUEADOOS;
import static parqueadero.util.ConstanteManager.MSJ_PARQUEADERO_VEHICULO;
import static parqueadero.util.ConstanteManager.MSJ_VEHICULO_NO_AUTORIZADO;
import static parqueadero.util.ConstanteManager.MSJ_VEHICULO_YA_ESTA_INGRESADO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IAdministradorParqueaderoServicio;
import parqueadero.servicio.IVigilanteServicio;
import parqueadero.util.ConstanteManager;
import parqueadero.util.Util;

public class Vigilante {
    
    private IVigilanteServicio vigilanteServicio;
    private IAdministradorParqueaderoServicio administradorParqueaderoServicio;
    
    public Vigilante (IVigilanteServicio vigilanteServicio, IAdministradorParqueaderoServicio administradorParqueaderoServicio) {
        this.vigilanteServicio = vigilanteServicio;
        this.administradorParqueaderoServicio = administradorParqueaderoServicio;
    }
    
    public GestionVehiculo registrarIngresoVehiculoAParqueadero(Vehiculo vehiculo, Date fechaIngreso) {
        
        GestionVehiculo gestionVehiculo = null;
        
        if (placaIniciaA(vehiculo.getPlaca())) {
            validarDiaDomingoLunes(fechaIngreso);
        }
        
        validarDisponibilidad (vehiculo);
        
        if (estaVehiculoIngresado(vehiculo)) {
            throw new ParqueaderoException(MSJ_VEHICULO_YA_ESTA_INGRESADO);
        }
        
        if (vehiculo instanceof RequestVehiculo) {
            
            if (EnumTipoVehiculo.MOTO.equals(vehiculo.getTipoVehiculo())) {
                vehiculo = ((RequestVehiculo) vehiculo).getMoto();
            } else if (EnumTipoVehiculo.CARRO.equals(vehiculo.getTipoVehiculo())) {
                vehiculo = ((RequestVehiculo) vehiculo).getCarro();
            } else {
                vehiculo = ((RequestVehiculo) vehiculo).getVehiculo(); 
            }
        }
        
        if (vehiculo instanceof Moto || vehiculo instanceof Carro) {
            gestionVehiculo = ingresarVehiculo(vehiculo, fechaIngreso);
        } else {
            throw new ParqueaderoException(MSJ_PARQUEADERO_VEHICULO);
        }
        
        return gestionVehiculo;
    }

    public void desocuparParqueadero () {
        vigilanteServicio.desocuparParqueadero();
    }
    
    public void validarDisponibilidad(Vehiculo vehiculo) {
        List<GestionVehiculo> vehiculosParqueados = obtenerVehiculosEnElParqueadero(administradorParqueaderoServicio.obtenerVehiculosEnElParqueadero(), vehiculo.getTipoVehiculo());
        if (EnumTipoVehiculo.MOTO.equals(vehiculo.getTipoVehiculo()) && vehiculosParqueados.size() >= 10) {
                throw new ParqueaderoException(MSJ_MAXIMO_MOTOS_PARQUEADOOS);
        } else if (EnumTipoVehiculo.CARRO.equals(vehiculo.getTipoVehiculo()) && vehiculosParqueados.size() >= 20) {
                throw new ParqueaderoException(MSJ_MAXIMO_CARROS_PARQUEADOS);
        }
    }

    private List<GestionVehiculo> obtenerVehiculosEnElParqueadero(List<GestionVehiculo> todosVehiculosEnElParqueadero, EnumTipoVehiculo tipoVehiculo) {
        List<GestionVehiculo> listVehiculo = new ArrayList<>();
        for (GestionVehiculo vehiculo: todosVehiculosEnElParqueadero) {
            if (tipoVehiculo.equals(vehiculo.getVehiculo().getTipoVehiculo())) {
                listVehiculo.add(vehiculo);
            }
        }
        return listVehiculo;
    }

    public void registrarSalidaVehiculoParqueadero(Vehiculo vehiculo, Date fechaSalida) {
        
        GestionVehiculo salidaVehiculo = vigilanteServicio.estaVehiculoIngresado(vehiculo);
        if (salidaVehiculo != null) {
            salidaVehiculo.setEstadoParqueo(EnumEstadoParqueo.SALIDA);
            salidaVehiculo.setFechaSalida(fechaSalida);
            vigilanteServicio.registrarSalidaVehiculo(salidaVehiculo);
        }
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
        float totalAPagar;
        
        List<TarifaXTipoVehiculo> listTarifa = administradorParqueaderoServicio.obtenerTarifasXTipoVehiculo();
        RecargoCilindraje recargoVehiculo = administradorParqueaderoServicio.obtenerRecargo(vehiculo);
        
        int diasEntreDosFechas  = Util.getDiasEntreDosFechas(fechaIngreso, fechaSalida);
        int horasEntreDosFechas = Util.getHorasEntreDosFechas(fechaIngreso, fechaSalida);
        
        if (horasEntreDosFechas >= 9) {
            diasEntreDosFechas += 1;
            horasEntreDosFechas = 0;
        }
        
        totalAPagar = calcularCobroPorDia (listTarifa, vehiculo, diasEntreDosFechas);
        totalAPagar += calcularCobroPorHora (listTarifa, vehiculo, horasEntreDosFechas);
        
        if (recargoVehiculo != null) {
            totalAPagar +=  recargoVehiculo.getValor();
        }
        
        return totalAPagar;
    }
    
    public float calcularCobroPorDia (List<TarifaXTipoVehiculo> listTarifa, Vehiculo vehiculo, int diasEntreDosFechas) {
        float totalAPagar = 0.0f;
        if (diasEntreDosFechas > 0) {
            Optional<TarifaXTipoVehiculo> optTarifaDia = listTarifa.stream()
                    .filter(t -> t.getTipoVehiculo().equals(vehiculo.getTipoVehiculo()) && EnumTiempo.DIA.equals(t.getUnidadTiempo()))
                    .findFirst();
            if (optTarifaDia.isPresent()) {
                totalAPagar = optTarifaDia.get().getValor() * diasEntreDosFechas;
            }
        }
        return totalAPagar;
    }

    public float calcularCobroPorHora (List<TarifaXTipoVehiculo> listTarifa, Vehiculo vehiculo, int horasEntreDosFechas) {
        float totalAPagar = 0.0f;
        if (horasEntreDosFechas > 0) {
            Optional<TarifaXTipoVehiculo> optTarifaDia = listTarifa.stream()
                    .filter(t -> t.getTipoVehiculo().equals(vehiculo.getTipoVehiculo()) && EnumTiempo.HORA.equals(t.getUnidadTiempo()))
                    .findFirst();
            if (optTarifaDia.isPresent()) {
                totalAPagar = optTarifaDia.get().getValor() * horasEntreDosFechas;
            }
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

    private GestionVehiculo ingresarVehiculo (Vehiculo vehiculo, Date fechaIngreso) {
        
        vigilanteServicio.registrarPlacaVehiculo(vehiculo);
        
        GestionVehiculo ingresoVehiculo = new GestionVehiculo(vehiculo, fechaIngreso);
        ingresoVehiculo.setEstadoParqueo(EnumEstadoParqueo.INGRESADO);
        ingresoVehiculo.setFechaIngreso(fechaIngreso);
        
        return vigilanteServicio.registrarIngresoVehiculo(ingresoVehiculo);
    }
    
    public GestionVehiculo obtenerVehiculoIngresado(Vehiculo vehiculo) {
        return vigilanteServicio.estaVehiculoIngresado(vehiculo);
    }
    
    public boolean estaVehiculoIngresado(Vehiculo vehiculo) {
        return vigilanteServicio.estaVehiculoIngresado(vehiculo) != null;
    }
    
    public GestionVehiculo registrarSalidaVehiculo(String placa) {
        GestionVehiculo gestionVehiculo = obtenerVehiculoIngresado(new Vehiculo(placa));
        if (gestionVehiculo != null) {
            gestionVehiculo.setEstadoParqueo(EnumEstadoParqueo.SALIDA);
            gestionVehiculo.setFechaSalida(new Date());
            gestionVehiculo.setValor(generarCobroVechiculoParqueo(gestionVehiculo));

            gestionVehiculo = vigilanteServicio.registrarSalidaVehiculo(gestionVehiculo);
        } else {
            throw new ParqueaderoException(ConstanteManager.MSJ_VEHICULO_NO_ESTA_INGRESADO);
        }
        
        return gestionVehiculo;
    }
}
