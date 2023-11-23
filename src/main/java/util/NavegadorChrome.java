/*
 * Classe que inicializa o navegador Chrome.
 */
package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * @author Cristiana
 */
public class NavegadorChrome {
    private static WebDriver driver;
    public static WebDriver Inicializar(String caminhoDriver){    
        System.setProperty("webdriver.chrome.driver", caminhoDriver+"\\chromedriver_win32\\chromedriver.exe");

        //DesiredCapabilities dc = DesiredCapabilities.chrome();
        //String userProfile= "C:\\ROBOS\\Chrome\\User Data\\Default\\Default"; //Pasta default
        //String userProfile= "C:\\Users\\Administrador\\Configurações locais\\Dados de aplicativos\\Google\\Chrome\\User Data\\Default\\Default";
        //String userProfile= "C:\\Users\\informatica_Inter\\AppData\\Local\\Google\\Chrome\\User Data\\Default"; //Meu computador, Computado Logistica02 (Mayara)

        String userProfile = "C:\\Documents and Settings\\Administrador\\Configurações locais\\Dados de aplicativos\\Google\\Chrome\\User Data\\Default";  //Windows Xp Maioria
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir="+userProfile);
        options.addArguments("--start-maximized");
        return driver = new ChromeDriver(options);
    }
    
}
