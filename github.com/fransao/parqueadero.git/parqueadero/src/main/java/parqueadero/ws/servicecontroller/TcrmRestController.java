package parqueadero.ws.servicecontroller;

import java.rmi.RemoteException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;
import parqueadero.exception.ParqueaderoException;

@RestController
@RequestMapping("/parqueadero")
@EnableAutoConfiguration
public class TcrmRestController {
    
    /**
     * Web Service end point
     */
    private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/vehiculo/tcrm", method=RequestMethod.GET)
    public float consultarVehiculosEnParqueadero() throws RemoteException {
        TCRMServicesInterface proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
        TcrmResponse tcrmResponse;
        try {
            tcrmResponse = proxy.queryTCRM(null);
            if (tcrmResponse != null) {
                return tcrmResponse.getValue();
            }
        } catch (RemoteException e) {
            throw new ParqueaderoException(e.getMessage());
        }

        return 0.0f;

    }
    
}
