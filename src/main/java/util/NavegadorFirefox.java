/*
 * Classe que inicializa o navegador Firefox.
 */
package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Sisco
 */
public class NavegadorFirefox {
   private static WebDriver driver;
   
   public static WebDriver Inicializar(String caminhoDriver){ 
        return driver = new FirefoxDriver();
    }
}
