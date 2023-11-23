/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Sisco
 */
public class PageUtils {
    
    public static boolean loopExisteTexto(String texto1){
        return loopExisteTexto(texto1,null, null);
    }
    
    public static boolean loopExisteTexto(String texto1, int tentativas){
        return loopExisteTexto(texto1,null, null, tentativas);
    }
    
    public static boolean loopExisteTexto(String texto1, String texto2){
        return loopExisteTexto(texto1,texto2, null);
    }
    
    public static boolean loopExisteTexto(String texto1, String texto2, int tentativas){
        return loopExisteTexto(texto1,texto2, null, tentativas);
    }
    
    public static boolean loopExisteTexto(String texto1, String texto2, String texto3){
        return loopExisteTexto(texto1,texto2, texto3, 60);
    }    
    
    public static boolean loopExisteTexto(String texto1, String texto2, String texto3, int tentativas){
        boolean encontrado = false;
        int qtdeArgs = 0;
        if(texto1 != null){
            qtdeArgs = 1;
            if(texto2 != null){
                qtdeArgs = 2;
                if(texto3 != null){
                    qtdeArgs = 3;
                }
            }
        }
        
        if(qtdeArgs == 0){
            throw new IllegalArgumentException("Parametros anteriores não informados.");
        }
        boolean contemTexto = false;
        int countTentativas = 0;
        do{ 
            switch(qtdeArgs){
                case 1:
                    contemTexto = existeTexto(texto1);
                    break;
                case 2:
                    contemTexto = existeTexto(texto1) || existeTexto(texto2);
                    break;
                case 3:
                    contemTexto = existeTexto(texto1) || existeTexto(texto2) || existeTexto(texto3);
            }
            
            if(contemTexto){
                encontrado = true;
            }else{
                countTentativas++;
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    System.out.println(e.toString());
                }
                
            } 
        }while(!encontrado && countTentativas<=tentativas);//5
        System.out.println("## texto(s)"+(encontrado?"":" não")+" encontrado(s) ##");
        
        return encontrado;
    }
    
    public static boolean existeTexto(String texto){
        if(texto != null){
            try{
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL); 
                robot.keyPress(KeyEvent.VK_C);  
                robot.keyRelease(KeyEvent.VK_C);  
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(500);

                String areaCopiada = "";
                areaCopiada = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                return areaCopiada.contains(texto);
            }catch(UnsupportedFlavorException e){
                System.out.println(e.toString());
            }catch(IOException e){
                System.out.println(e.toString());
            }catch(InterruptedException e){
                System.out.println(e.toString());
            }catch(AWTException e){
                System.out.println(e.toString());
            }
        }        
        return false;
    }
    
    public static void limpaCacheNavegador(WebDriver driver) throws InterruptedException, AWTException {
        driver.get("chrome://settings/clearBrowserData");
        Thread.sleep(5000);
//        driver.findElement(By.id("clear-browser-data-commit")).click();
        Robot r = new Robot();
        for (int i = 0; i < 9; i++) {
            r.keyPress(KeyEvent.VK_TAB);
            r.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(1000);
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(10000);
    }
}
