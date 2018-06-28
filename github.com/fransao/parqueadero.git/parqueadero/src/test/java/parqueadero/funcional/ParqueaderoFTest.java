package parqueadero.funcional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import parqueadero.util.Util;

public class ParqueaderoFTest {

    private static final String URL_REGISTRAR_INGRESO_VEHICULO = "http://localhost:4200/vehiculo/create";
    private static final String URL_REGISTRAR_SALIDA_VEHICULO  = "http://localhost:4200/vehiculo/salida";
    private static final String URL_CONSULTAR_VEHICULOS_PARQUEADERO = "http://localhost:4200/vehiculo";
    
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
    public void comprobarFlujoCorrectoObtenerVehicilosEnElParqueadero() throws InterruptedException {
        driver.get(URL_REGISTRAR_SALIDA_VEHICULO);
        
        WebElement placaElemento = driver.findElement(By.id("placa"));
        placaElemento.sendKeys(PLACA_CARRO);
        
        WebElement botonAceptarElemento = driver.findElement(By.name("consultar"));
        botonAceptarElemento.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 5);
        
        WebElement placaElement = driver.findElement(By.id("mensaje"));
        
        wait.until(ExpectedConditions.visibilityOf(placaElement));
        
        Assert.assertTrue("Se registro la salida del vehiculo", !Util.isEmpty(placaElement.getText()) && placaElement.getText().contains("exitosa"));
        
    }
    
    @Test
    public void comprobarFlujoCorrectoRegistrarSalidaVehiculo() throws InterruptedException {
        driver.get(URL_CONSULTAR_VEHICULOS_PARQUEADERO);
        
        WebDriverWait wait = new WebDriverWait(driver, 5);
        
        WebElement vehiculosElement = driver.findElement(By.id("vehiculos"));
        
        wait.until(ExpectedConditions.visibilityOf(vehiculosElement));
        
        Assert.assertTrue("Se registro la salida del vehiculo", vehiculosElement.isDisplayed());
    }

}
