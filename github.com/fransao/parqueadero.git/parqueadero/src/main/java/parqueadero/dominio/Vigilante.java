package parqueadero.dominio;

import java.util.Calendar;
import java.util.Date;

import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.exception.ParqueaderoException;
import parqueadero.servicio.IGestionVehiculoServicio;

public class Vigilante {

    private static final String MSJ_PARQUEADERO_VEHICULO   = "El parqueadero solo permite ingresar Carros y Motos";
    public static final  String MSJ_VEHICULO_NO_AUTORIZADO = "El vehiculo no esta autorizado para ingresar (puede ingresar el domingo o lunes)";
    
    private IGestionVehiculoServicio gestionVehiculoServicio;
    
    public Vigilante (IGestionVehiculoServicio gestionVehiculoServicio) {
        this.gestionVehiculoServicio = gestionVehiculoServicio;
    }
    
    public void ingresarVehiculo(Vehiculo vehiculo, Date fechaIngreso) {
        
        if (placaIniciaA(vehiculo.getPlaca())) {
            validarDiaDomingoLunes(fechaIngreso);
        }
        
        if (vehiculo instanceof Moto) {
            ingresarMoto((Moto) vehiculo, fechaIngreso);
        } else if (vehiculo instanceof Carro) {
            ingresarCarro((Carro) vehiculo, fechaIngreso);
        } else {
            throw new ParqueaderoException(MSJ_PARQUEADERO_VEHICULO);
        }
        
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

    private void ingresarMoto (Moto moto, Date fechaIngreso) {
        GestionVehiculo ingresoVehiculo = new GestionVehiculo(EnumTipoVehiculo.MOTO, moto, fechaIngreso);
        gestionVehiculoServicio.ingresarVehiculo(ingresoVehiculo);
    }
    
    private void ingresarCarro(Carro carro, Date fechaIngreso) {
        GestionVehiculo ingresoVehiculo = new GestionVehiculo(EnumTipoVehiculo.CARRO, carro, fechaIngreso);
        gestionVehiculoServicio.ingresarVehiculo(ingresoVehiculo);
    }

    public boolean estaVehiculoIngresado(Moto moto) {
        return gestionVehiculoServicio.estaVehiculoIngresado(moto) != null;
    }
    
}
