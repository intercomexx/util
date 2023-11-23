/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import util.controller.Controller;

/**
 * @author Sisco
 */
public class RobotError {

    private Exception exception;
    private WebDriver driver;
    private String caminhoRobo;
    private String nomeRobo;
    private String tipoRobo;
    private String textoErroAdicional;
    private String caminhoArquivoErro = "";
    private String caminhoPrintErro = "";
    private String caminhoHtmlErro = "";
    private ConexaoBD conexao;
    private Controller controller;
    private LogUtil LOGGER = new LogUtil(RobotError.class);
    private int qtdeErros;

    public RobotError() {
    }

    public RobotError(Exception exception, String caminhoRobo) {
        this.exception = exception;
        this.caminhoRobo = caminhoRobo;
    }

    public RobotError(Exception exception, WebDriver driver, String caminhoRobo) {
        this(exception, caminhoRobo);
        this.driver = driver;
    }

    public RobotError(Exception exception, String textoErroAdicional, String caminhoRobo) {
        this(exception, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    public RobotError(Exception exception, String textoErroAdicional, WebDriver driver, String caminhoRobo) {
        this(exception, driver, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    public RobotError(Exception exception, ConexaoBD conexao, String nomeRobo, String caminhoRobo) {
        this(exception, caminhoRobo);
        this.conexao = conexao;
        this.nomeRobo = nomeRobo;
    }

    public RobotError(Exception exception, ConexaoBD conexao, String nomeRobo, String tipoRobo, String caminhoRobo) {
        this(exception, conexao, nomeRobo, caminhoRobo);
        this.tipoRobo = tipoRobo;
    }

    public RobotError(Exception exception, ConexaoBD conexao, String nomeRobo, WebDriver driver, String caminhoRobo) {
        this(exception, conexao, nomeRobo, caminhoRobo);
        this.driver = driver;
    }

    public RobotError(Exception exception, ConexaoBD conexao, String nomeRobo, String tipoRobo, WebDriver driver, String caminhoRobo) {
        this(exception, conexao, nomeRobo, driver, caminhoRobo);
        this.tipoRobo = tipoRobo;
    }

    public RobotError(Exception exception, String textoErroAdicional, ConexaoBD conexao, String nomeRobo, String caminhoRobo) {
        this(exception, conexao, nomeRobo, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    public RobotError(Exception exception, String textoErroAdicional, ConexaoBD conexao, String nomeRobo, String tipoRobo, String caminhoRobo) {
        this(exception, conexao, nomeRobo, tipoRobo, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    public RobotError(Exception exception, String textoErroAdicional, ConexaoBD conexao, String nomeRobo, WebDriver driver, String caminhoRobo) {
        this(exception, conexao, nomeRobo, driver, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    public RobotError(Exception exception, String textoErroAdicional, ConexaoBD conexao, String nomeRobo, String tipoRobo, WebDriver driver, String caminhoRobo) {
        this(exception, conexao, nomeRobo, tipoRobo, driver, caminhoRobo);
        this.textoErroAdicional = textoErroAdicional;
    }

    private boolean inicializarConexao() throws InterruptedException {
        this.conexao = new ConexaoBD();
        boolean conectado = this.conexao.connect();
        LOGGER.getLoggerInfo("Banco conectado: " + conectado);

        if (!conectado) {
            LOGGER.getLoggerInfo("CONEXÃO COM BANCO DE DADOS NÃO FOI ESTABELECIDA! REINICIE O SISTEMA!");
            return false;
        }
        this.controller = new Controller(this.conexao.getConexao());
        return true;
    }

    public void desconectarBD() {
        LOGGER.getLoggerInfo("Banco desconectado: " + this.conexao.disconnect());
    }

    public void criarArqErro(Exception exception) {
        if (exception != null) {
            try {
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                this.caminhoArquivoErro = this.caminhoRobo + "\\ERRO.txt";
                BufferedWriter arquivo = new BufferedWriter(new FileWriter(this.caminhoArquivoErro));
                PrintWriter printArq = new PrintWriter(arquivo);
                printArq.println("========= " + date + " =========");
                printArq.println("== StackTrace do Erro ==");
                exception.printStackTrace(printArq);
                if (this.textoErroAdicional != null) {
                    printArq.println(this.textoErroAdicional);
                }
                arquivo.close();
            } catch (NullPointerException e) {
                LOGGER.getLoggerInfo(e.toString());
            } catch (IOException io) {
                LOGGER.getLoggerInfo(io.toString());
            }
        }
    }

    public String criarArqErro(Exception exception, String caminho) {
        if (exception != null) {
            try {
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String dateErro = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
                String caminhoArquivoErro = caminho + "\\ERRO-" + dateErro + ".txt";
                this.caminhoArquivoErro = caminhoArquivoErro;
                BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoArquivoErro));
                PrintWriter printArq = new PrintWriter(arquivo);
                printArq.println("========= " + date + " =========");
                printArq.println("== StackTrace do Erro ==");
                exception.printStackTrace(printArq);
                if (this.textoErroAdicional != null) {
                    printArq.println(this.textoErroAdicional);
                }
                arquivo.close();
                return caminhoArquivoErro;
            } catch (NullPointerException e) {
                LOGGER.getLoggerInfo(e.toString());
            } catch (IOException io) {
                LOGGER.getLoggerInfo(io.toString());
            }
        }
        return null;
    }

    public void criarArqHtml() {
        if (this.driver != null) {
            try {
                if (DriverUtils.existeElementos(driver, By.xpath("//*"))) {
                    Date d = new Date();
                    String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
                    this.caminhoHtmlErro = this.caminhoRobo + "\\html.txt";
                    LOGGER.getLoggerInfo("caminho html: " + this.caminhoHtmlErro);
                    BufferedWriter arquivo = new BufferedWriter(new FileWriter(this.caminhoHtmlErro));
                    PrintWriter printArq = new PrintWriter(arquivo);
                    printArq.println("========= " + date + " =========");
                    printArq.println("== HTML da Página ==");
                    printArq.println(this.driver.findElement(By.xpath("//*")).getAttribute("outerHTML"));
                    arquivo.close();
                }
            } catch (NullPointerException e) {
                LOGGER.getLoggerInfo(e.toString());
            } catch (IOException io) {
                LOGGER.getLoggerInfo(io.toString());
            } catch (WebDriverException we) {
                LOGGER.getLoggerInfo(we.toString());

            }
        }
    }

    private String criarArqHtml(WebDriver driver, String caminhoHtml) {
        if (driver != null) {
            try {
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String caminhoArquivoHtml = caminhoHtml + "\\html.txt";
                this.caminhoHtmlErro = caminhoArquivoHtml;
                LOGGER.getLoggerInfo("caminho html: " + caminhoArquivoHtml);
                BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoArquivoHtml));
                PrintWriter printArq = new PrintWriter(arquivo);
                printArq.println("========= " + date + " =========");
                printArq.println("== HTML da Página ==");
                printArq.println(driver.findElement(By.xpath("//*")).getAttribute("outerHTML"));
                arquivo.close();

                return caminhoArquivoHtml;
            } catch (NullPointerException e) {
                LOGGER.getLoggerInfo(e.toString());
            } catch (IOException io) {
                LOGGER.getLoggerInfo(io.toString());
            }
        }
        return null;
    }

    public void salvarImagem() throws AWTException, IOException, InterruptedException {
        LOGGER.getLoggerInfo("SALVANDO IMAGEM......");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        LOGGER.getLoggerInfo("Height: " + height);
        Robot robot = new Robot();

        BufferedImage img = null;
        Rectangle r = new Rectangle(width, height);
        //r.x = 85;
        img = robot.createScreenCapture(r);
        this.caminhoPrintErro = this.caminhoRobo + "/print_erro.png";
        ImageIO.write(img, "png", new File(this.caminhoPrintErro));
    }

    public String salvarImagem(String caminho) throws AWTException, IOException, InterruptedException {
        String date = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
        LOGGER.getLoggerInfo("SALVANDO IMAGEM DO ERROR...");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        LOGGER.getLoggerInfo("Height: " + height);
        Robot robot = new Robot();

        BufferedImage img = null;
        Rectangle r = new Rectangle(width, height);

        img = robot.createScreenCapture(r);
        String caminhoArquivoImagem = caminho + "\\" + date + ".png";
        this.caminhoPrintErro = caminhoArquivoImagem;
        ImageIO.write(img, "png", new File(caminhoArquivoImagem));
        return caminhoArquivoImagem;
    }

    public List<String> processarEnvioDeErro(Exception e, String caminhoErro, String caminhoPrint, WebDriver driver, String caminhoHtml) throws IOException, InterruptedException, AWTException {
        List<String> listaDeCaminhos = new ArrayList<>();
        LOGGER.getLoggerInfo("DEU ERRO !!!");
        List objetos = Arrays.asList(e, caminhoErro, caminhoPrint, driver, caminhoHtml);
        objetos.forEach(o -> {
            if (o == null){
                LOGGER.getLoggerInfo("ERRO NO METODO processarEnvioDeErro()");
            }
        });
        if (caminhoPrint != null) {
            String caminhoArquivoImagem = salvarImagem(caminhoPrint);
            listaDeCaminhos.add(caminhoArquivoImagem);
        }else {
            listaDeCaminhos.add(null);
        }
        e.printStackTrace();
        if(caminhoErro != null && e != null){
            String caminhoArquivoErro = criarArqErro(e, caminhoErro);
            listaDeCaminhos.add(caminhoArquivoErro);
        }else {
            listaDeCaminhos.add(null);
        }
        if (driver != null) {
            String caminhoArquivoHtml = criarArqHtml(driver, caminhoHtml);
            listaDeCaminhos.add(caminhoArquivoHtml);
        }else {
            listaDeCaminhos.add(null);
        }
        return listaDeCaminhos;
    }

    public void processarEnvioDeErro(String caminhoPrint, WebDriver driver, String caminhoHtml) throws IOException, InterruptedException, AWTException {
        System.err.println("DEU ERRO !!!");
        List objetos = Arrays.asList(caminhoPrint, driver, caminhoHtml);
        objetos.forEach(o -> {
            if (o == null){
                LOGGER.getLoggerInfo("ERRO NO METODO processarEnvioDeErro()");
            }
        });
        if (caminhoPrint != null) {
            salvarImagem(caminhoPrint);
        }
        if (driver != null) {
            criarArqHtml(driver, caminhoHtml);
        }
    }

    public void deletaArquivos(String diretorio) {
        File diretorioNovo = new File(diretorio);
        LOGGER.getLoggerInfo("Deletando aquivos do caminho " + diretorio);
        Arrays.stream(Objects.requireNonNull(diretorioNovo.listFiles())).forEach(File::delete);
    }

    private void processarEnviarErro(boolean limpaCache) throws AWTException, IOException, InterruptedException {
        LOGGER.getLoggerInfo("DEU ERRO!!!");
        this.exception.printStackTrace();

        salvarImagem();

        LOGGER.getLoggerInfo("In");
        if (this.driver != null) {
            criarArqHtml();

            if (limpaCache) {
                PageUtils.limpaCacheNavegador(this.driver);
            }
            this.driver.quit();
        }

        criarArqErro(this.exception);

        if (this.conexao != null && inicializarConexao()) {
            this.controller.enviarMensagemErroResult(this.nomeRobo, this.tipoRobo, this.caminhoArquivoErro, this.caminhoPrintErro, this.caminhoHtmlErro);
            desconectarBD();
        }
    }

    public void processarEnviarErro() throws AWTException, IOException, InterruptedException {
        processarEnviarErro(false);
    }

    public void processarEnviarErroLimpaCache() throws AWTException, IOException, InterruptedException {
        processarEnviarErro(true);
    }

    public String getCaminhoArquivoErro() {
        return this.caminhoArquivoErro;
    }

    public String getCaminhoPrintErro() {
        return this.caminhoPrintErro;
    }

    public String getCaminhoHtmlErro() {
        return this.caminhoHtmlErro;
    }
}
