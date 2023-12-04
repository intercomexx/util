/*
 * Classe que inicializa o navegador Chrome.
 */
package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.LoggerFactory;


/**
 * @author Cristiana
 */
public class NavegadorChrome {
    private static WebDriver driver;
    private static String driverVersao = "chromedriver108.exe";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NavegadorChrome.class);
    public static WebDriver Inicializar(String caminhoDriver){    
        System.setProperty("webdriver.chrome.driver", caminhoDriver + "\\chromedriver_win32\\" + driverVersao);
        LOGGER.info("INICIALIZANDO CHROME COM CHROME DRIVER VERSÃO: " + driverVersao);
        ChromeOptions options = new ChromeOptions();
        String userProfile = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(options);
        return driver;
    }
    
}
