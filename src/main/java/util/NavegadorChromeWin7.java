///*
// * Classe que inicializa o navegador Chrome no Windows 7
// */
//package util;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
///**
// * @author Cristiana
// */
//public class NavegadorChromeWin7{
//    private static WebDriver driver;
//
//    public static WebDriver Inicializar(String caminhoDriver){
//        System.setProperty("webdriver.chrome.driver", caminhoDriver+"\\chromedriver_win32\\chromedriver.exe");
//        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        //dc.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
//        //driver = new ChromeDriver(dc);
//
//
//        //String userProfile= "C:\\Users\\Administrador\\Configurações locais\\Dados de aplicativos\\Google\\Chrome\\User Data\\Default\\Default";
//        //String userProfile= "C:\\Users\\informatica_Inter\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
//        //String userProfile = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
//        String userProfile = "C:\\Users\\Importaca-03\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("user-data-dir="+userProfile);
//        options.addArguments("--start-maximized");
//
//        //Map<String, Object> chromeOptions = new Map<String, Object>();
//        //chromeOptions.put("binary", "/usr/lib/chromium-browser/chromium-browser");
//        //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        dc.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new ChromeDriver(dc);
//
//
//        return driver = new ChromeDriver(options);
//    }
//}
