package parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"parqueadero.*","parqueadero.repositorio"})
public class ParqueaderoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParqueaderoApplication.class, args);
    }
}
