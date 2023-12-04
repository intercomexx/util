/*
 * Classe que inicializa o navegador Chrome.
 */
package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;


/**
 * @author Cristiana
 */
public class NavegadorChrome {
    private static WebDriver driver;
    public static WebDriver Inicializar(String caminhoDriver){    
        System.setProperty("webdriver.chrome.driver", caminhoDriver+"\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        String userProfile = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(options);
        return driver;
    }
    
}
