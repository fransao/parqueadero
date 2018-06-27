package parqueadero.persistencia.builder;

import parqueadero.dominio.Carro;
import parqueadero.dominio.EstadoParqueo;
import parqueadero.dominio.GestionVehiculo;
import parqueadero.dominio.Moto;
import parqueadero.dominio.RecargoCilindraje;
import parqueadero.dominio.TarifaXTipoVehiculo;
import parqueadero.dominio.TipoVehiculo;
import parqueadero.dominio.Vehiculo;
import parqueadero.enumerado.EnumEstadoParqueo;
import parqueadero.enumerado.EnumTiempo;
import parqueadero.enumerado.EnumTipoVehiculo;
import parqueadero.persistencia.entidad.EstadoParqueoEntidad;
import parqueadero.persistencia.entidad.GestionVehiculoEntidad;
import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidad;
import parqueadero.persistencia.entidad.TarifaXTipoVehiculoEntidadPK;
import parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import parqueadero.persistencia.entidad.VehiculoEntidad;

public class VehiculoBuilder {

    private VehiculoBuilder () {
        
    }
    
    public static Vehiculo convertirVehiculoADominio (VehiculoEntidad vehiculoEntidad) {
        Vehiculo vehiculo = new Vehiculo (vehiculoEntidad.getPlaca(), tipoVehiculoAEnumerado(vehiculoEntidad.getTipoVehiculo().getTipoVehiculo()));
        if (EnumTipoVehiculo.MOTO.equals(EnumTipoVehiculo.getEnumTipoVehiculo(vehiculoEntidad.getTipoVehiculo().getTipoVehiculo()))) {
            return new Moto (vehiculo, vehiculoEntidad.getCilindraje());
        }
        if (EnumTipoVehiculo.CARRO.equals(EnumTipoVehiculo.getEnumTipoVehiculo(vehiculoEntidad.getTipoVehiculo().getTipoVehiculo()))) {
            return new Carro (vehiculo);
        }
        return vehiculo;
    }
    
    private static EnumTipoVehiculo tipoVehiculoAEnumerado(Integer tipoVehiculo) {
        return EnumTipoVehiculo.getEnumTipoVehiculo(tipoVehiculo);
    }

    public static VehiculoEntidad convertirVehiculoAEntidad (Vehiculo vehiculo) {
        
        VehiculoEntidad vehiculoEntidad = new VehiculoEntidad();
        vehiculoEntidad.setPlaca(vehiculo.getPlaca());
        vehiculoEntidad.setTipoVehiculo(getTipoVehiculo(vehiculo));
        if (vehiculo instanceof Moto) {
          vehiculoEntidad.setCilindraje(getCilindrajeVehiculo(vehiculo));
        }
        
        
        return vehiculoEntidad;
    }
    
    private static int getCilindrajeVehiculo (Vehiculo vehiculo) {
        int cilindraje = 0;
        if (vehiculo instanceof Moto) {
            cilindraje = ((Moto) vehiculo).getCilindraje();
        }
        return cilindraje;
    }
    
    private static TipoVehiculoEntidad getTipoVehiculo (Vehiculo vehiculo) {
        TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
        
        if (vehiculo instanceof Carro) {
            tipoVehiculoEntidad.setTipoVehiculo(EnumTipoVehiculo.CARRO.getTipoVehiculo());
        } else if (vehiculo instanceof Moto) {
            tipoVehiculoEntidad.setTipoVehiculo(EnumTipoVehiculo.MOTO.getTipoVehiculo());
        }
        
        return tipoVehiculoEntidad;
    }
    
    public static TipoVehiculo convertirTipoVehiculoADominio (TipoVehiculoEntidad tipoVehiculoEntidad) {
        TipoVehiculo tipoVehiculo = null;
        if (tipoVehiculoEntidad != null) {
            tipoVehiculo = new TipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(tipoVehiculoEntidad.getTipoVehiculo()), tipoVehiculoEntidad.getNombreTipoVehiculo());
        }
        return tipoVehiculo;
    }
    
    public static TipoVehiculoEntidad convertirTipoVehiculoAEntidad (TipoVehiculo tipoVehiculo) {
        TipoVehiculoEntidad tipoVehiculoEntidad = new TipoVehiculoEntidad();
        tipoVehiculoEntidad.setTipoVehiculo(tipoVehiculo.getCodTipoVehiculo().getTipoVehiculo());
        tipoVehiculoEntidad.setNombreTipoVehiculo(tipoVehiculo.getNombreTipoVehiculo());
        
        return tipoVehiculoEntidad;
    }
    
    public static GestionVehiculoEntidad convertirGestionVehiculoAEntidad (GestionVehiculo gestionVehiculo) {
        GestionVehiculoEntidad gestionVehiculoEntidad = new GestionVehiculoEntidad();
        gestionVehiculoEntidad.setSecuenciaParqueoVehiculo(gestionVehiculo.getSecuenciaParqueoVehiculo());
        gestionVehiculoEntidad.setVehiculo(convertirVehiculoAEntidad(gestionVehiculo.getVehiculo()));
        gestionVehiculoEntidad.setEstadoParqueo(convertirEstadoParqueoAEstadoParqueoEntidad(gestionVehiculo));
        gestionVehiculoEntidad.setFechaIngreso(gestionVehiculo.getFechaIngreso());
        gestionVehiculoEntidad.setFechaSalida(gestionVehiculo.getFechaSalida());
        gestionVehiculoEntidad.setValor(gestionVehiculo.getValor());
        
        return gestionVehiculoEntidad;
    }

    public static GestionVehiculo convertirGestionVehiculoADominio (GestionVehiculoEntidad gestionVehiculoEntidad) {
        GestionVehiculo gestionVehiculo = new GestionVehiculo();
        gestionVehiculo.setSecuenciaParqueoVehiculo(gestionVehiculoEntidad.getSecuenciaParqueoVehiculo());
        gestionVehiculo.setVehiculo(convertirVehiculoADominio(gestionVehiculoEntidad.getVehiculo()));
        gestionVehiculo.setEstadoParqueo(EnumEstadoParqueo.getEnumEstadoParqueo(gestionVehiculoEntidad.getEstadoParqueo().getEstado()));
        gestionVehiculo.setFechaIngreso(gestionVehiculoEntidad.getFechaIngreso());
        gestionVehiculo.setFechaSalida(gestionVehiculoEntidad.getFechaSalida());
        gestionVehiculo.setValor(gestionVehiculoEntidad.getValor());
        
        return gestionVehiculo;
    }
    
    private static EstadoParqueoEntidad convertirEstadoParqueoAEstadoParqueoEntidad(GestionVehiculo gestionVehiculo) {
        EstadoParqueoEntidad estadoParqueoEntidad = new EstadoParqueoEntidad();
        estadoParqueoEntidad.setEstado(gestionVehiculo.getEstadoParqueo().getEstadoParqueo());
        
        return estadoParqueoEntidad;
    }

    public static EstadoParqueoEntidad convertirEstadoParqueoAEntidad(EstadoParqueo estadoParqueo) {
        EstadoParqueoEntidad estadoParqueoEntidad = new EstadoParqueoEntidad();
        estadoParqueoEntidad.setEstado(estadoParqueo.getEstadoParqueo().getEstadoParqueo());
        estadoParqueoEntidad.setNombreEstado(estadoParqueo.getNombreEstadoParqueo());
        
        return estadoParqueoEntidad;
    }

    public static EstadoParqueo convertirEstadoParqueoADominio(EstadoParqueoEntidad estadoParqueoEntidad) {
        EstadoParqueo estadoParqueo = new EstadoParqueo();
        estadoParqueo.setEstadoParqueo(EnumEstadoParqueo.getEnumEstadoParqueo(estadoParqueoEntidad.getEstado()));
        estadoParqueo.setNombreEstadoParqueo(estadoParqueoEntidad.getNombreEstado());
        
        return estadoParqueo;
    }

    public static TarifaXTipoVehiculo convertirTarifaXTipoVehiculoADomino (TarifaXTipoVehiculoEntidad tarifaEntidad) {
        TarifaXTipoVehiculo tarifaXTipoVehiculo = new TarifaXTipoVehiculo();
        tarifaXTipoVehiculo.setTipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(tarifaEntidad.getTarifaXTipoVehiculoPK().getTipoVehiculo()));
        tarifaXTipoVehiculo.setUnidadTiempo(EnumTiempo.getEnum(tarifaEntidad.getTarifaXTipoVehiculoPK().getUnidadTiempo()));
        tarifaXTipoVehiculo.setValor(tarifaEntidad.getValor());
        
        return tarifaXTipoVehiculo;
    }
    
    public static TarifaXTipoVehiculoEntidad convertirTarifaXTipoVehiculoAEntidad (TarifaXTipoVehiculo tarifaXTipoVehiculo) {
        TarifaXTipoVehiculoEntidad tarifaXTipoVehiculoEntidad = new TarifaXTipoVehiculoEntidad();
        tarifaXTipoVehiculoEntidad.setTarifaXTipoVehiculoPK(convertirTarifaTipoVehiculoAEntidadPK(tarifaXTipoVehiculo));
        tarifaXTipoVehiculoEntidad.setValor(tarifaXTipoVehiculo.getValor());
        
        return tarifaXTipoVehiculoEntidad;
    }

    private static TarifaXTipoVehiculoEntidadPK convertirTarifaTipoVehiculoAEntidadPK(TarifaXTipoVehiculo tarifaXTipoVehiculo) {
        TarifaXTipoVehiculoEntidadPK tarifaXTipoVehiculoEntidadPK = new TarifaXTipoVehiculoEntidadPK();
        tarifaXTipoVehiculoEntidadPK.setTipoVehiculo(tarifaXTipoVehiculo.getTipoVehiculo().getTipoVehiculo());
        tarifaXTipoVehiculoEntidadPK.setUnidadTiempoEntidad(tarifaXTipoVehiculo.getUnidadTiempo().getTiempo());
        
        return tarifaXTipoVehiculoEntidadPK;
    }
    
    public static RecargoCilindraje convertirRecargoEntidadADominio (RecargoCilindrajeEntidad recargoEntidad) {
        if (recargoEntidad == null) {
            return null;
        }
        
        RecargoCilindraje recargo = new RecargoCilindraje();
        recargo.setTipoVehiculo(EnumTipoVehiculo.getEnumTipoVehiculo(recargoEntidad.getTipoVehiculo()));
        recargo.setIdCilindraje(recargoEntidad.getIdCilindraje());
        recargo.setCilindrajeDesde(recargoEntidad.getCilindrajeDesde());
        recargo.setCilindrajeHasta(recargoEntidad.getCilindrajeHasta());
        recargo.setValor(recargoEntidad.getValor());
        
        return recargo;
    }
    
    public static RecargoCilindrajeEntidad convertirRecargoAEntidad (RecargoCilindraje recargo) {
        RecargoCilindrajeEntidad recargoEntidad = new RecargoCilindrajeEntidad();
        recargoEntidad.setTipoVehiculo(recargo.getTipoVehiculo().getTipoVehiculo());
        recargoEntidad.setIdCilindraje(recargo.getIdCilindraje());
        recargoEntidad.setCilindrajeDesde(recargo.getCilindrajeDesde());
        recargoEntidad.setCilindrajeHasta(recargo.getCilindrajeHasta());
        recargoEntidad.setValor(recargo.getValor());
        
        return recargoEntidad;
    }
    
}
