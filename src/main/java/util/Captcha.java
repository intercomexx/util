package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import static util.DriverUtils.foiEncontradoElementos;

public class Captcha {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Captcha.class);
    public static boolean quebraCaptcha(WebDriver driver){
        int tentativa = 0;
        LOGGER.info("## Iniciando resolução do captcha... ##");
        if (captchaApareceu(driver)){
            while (tentativa < 30){
                boolean captchaResolvido = foiEncontradoElementos(driver, By.cssSelector("[data-state*='solved']"), 1);
                boolean erroCaptcha = foiEncontradoElementos(driver, By.cssSelector("[data-state='error']"), 1);
                if(erroCaptcha){
                    LOGGER.info("## ERRO CAPTCHA ENCONTRANDO ##");
                    return false;
                }
                if (captchaResolvido){
                    LOGGER.info("## CAPTCHA RESOLVIDO... ##");
                    return true;
                }
                tentativa++;
            }
        }
        return false;
    }

    private static boolean captchaApareceu(WebDriver driver){
        boolean encontrouBtnCaptcha = foiEncontradoElementos(driver, By.className("captcha-solver-info"), 30);
        if (encontrouBtnCaptcha) {
            WebElement btnCaptcha = driver.findElement(By.className("captcha-solver-info"));
            LOGGER.info("CLICANDO NO BOTÃO PARA SOLUCIONAR CAPTCHA...");
            btnCaptcha.click();
            return true;
        }
        return false;
    }
}
