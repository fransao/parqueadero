package parqueadero.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import parqueadero.persistencia.entidad.RecargoCilindrajeEntidad;

public interface IRepositorioRecargo extends CrudRepository<RecargoCilindrajeEntidad, Integer> {

    @Query("SELECT rec FROM Recargo rec WHERE rec.tipoVehiculo = :paramTipoVehiculo AND :paramCilindraje BETWEEN rec.cilindrajeDesde AND rec.cilindrajeHasta ")
    public RecargoCilindrajeEntidad obtenerRecargoVehiculo(@Param("paramTipoVehiculo") int paramTipoVehiculo, @Param("paramCilindraje") int paramCilindraje);
     
}
