///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package util;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//public class NavegadorInternetExplorer {
//   private static WebDriver driver;
//
//   public static WebDriver Inicializar(String caminhoDriver){
//        System.setProperty("webdriver.ie.driver", caminhoDriver+"\\IEDriverServer_Win32_2.46.0\\IEDriverServer.exe");
//        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//        caps.setCapability("ignoreZoomSetting", true);
//        return driver = new InternetExplorerDriver(caps);
//    }
//
//}