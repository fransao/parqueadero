package parqueadero.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity (name="Moto")
@NamedQuery (name="Moto.obtenerMoto", query="SELECT moto FROM Moto")
public class MotoEntidad {

}
