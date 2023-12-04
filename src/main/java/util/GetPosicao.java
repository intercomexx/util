/*
 * Busca as coodernadas do mouse
 */
package util;

import org.slf4j.LoggerFactory;
import util.controller.Controller;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**@author Cristiana  
 */
public class GetPosicao{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GetPosicao.class);
    
    //Recupera a coodernadas(x, y) do mouse
    public static void forMouse(int cont) throws AWTException, InterruptedException{        
        for(int i=1; i<=cont; i++){
            PointerInfo p = MouseInfo.getPointerInfo();
            Point b = p.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            
            LOGGER.info("x: " + x);
            LOGGER.info("y: " + y);
        }
   }
    
   //Posiciona o mouse e clica com botão direito 
   private static void posicionarMouse(int x, int y) throws InterruptedException, AWTException{
        Robot r = new Robot();
        r.mouseMove(x, y);

        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(2000);   
   }
}
