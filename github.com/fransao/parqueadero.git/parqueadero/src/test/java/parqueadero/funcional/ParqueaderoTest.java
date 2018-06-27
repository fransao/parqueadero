package parqueadero.funcional;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import parqueadero.util.Util;

public class ParqueaderoTest {

    private static final String URL_REGISTRAR_INGRESO_VEHICULO = "http://localhost:4200/vehiculo/create";
    private static final String URL_REGISTRAR_SALIDA_VEHICULO  = "http://localhost:4200/vehiculo/salida";
    private static final CharSequence PLACA_CARRO = "BCD123";
    private static final CharSequence VEHICULO_CARRO = "CARRO";
    
    private static WebDriver driver = null;
    
    @BeforeClass
    public static void inicializarDriver () {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }
    
    @AfterClass
    public static void cerrarDriver () {
        driver.quit();
    }
    
    @Test
    public void comprobarFlujoCorrectoRegistrarIngresoVehiculo() {
        driver.get(URL_REGISTRAR_INGRESO_VEHICULO);
        
        WebElement placaElemento = driver.findElement(By.id("placa"));
        placaElemento.sendKeys(PLACA_CARRO);
        
        WebElement tipoVehiculoElemento = driver.findElement(By.id("tipoVehiculo"));
        tipoVehiculoElemento.sendKeys(VEHICULO_CARRO);
        
        WebElement botonAceptarElemento = driver.findElement(By.name("ingresar"));
        botonAceptarElemento.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement mensajeElement = driver.findElement(By.id("mensaje"));
        wait.until(ExpectedConditions.visibilityOf(mensajeElement));
        
        Assert.assertTrue("Se ingreso el vehiculo", mensajeElement.getText().contains("placa"));
        
    }

    @Test
    public void comprobarFlujoCorrectoRegistrarSalidaVehiculo() {
        driver.get(URL_REGISTRAR_SALIDA_VEHICULO);
        
        WebElement placaElemento = driver.findElement(By.id("placa"));
        placaElemento.sendKeys(PLACA_CARRO);
        
        WebElement botonAceptarElemento = driver.findElement(By.name("consultar"));
        botonAceptarElemento.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement valorPagarElement = driver.findElement(By.id("valorPagar"));        
        wait.until(ExpectedConditions.visibilityOf(valorPagarElement));
        
        float valorPagar = Util.isEmpty(valorPagarElement.getText()) ? 0.0f : Float.parseFloat(valorPagarElement.getText());
        
        Assert.assertTrue("Se registro la salida del vehiculo", valorPagar > 0.0f);
        
    }
    
}
