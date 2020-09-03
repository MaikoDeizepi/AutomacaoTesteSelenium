package tests;


import Screenshot.Screenshot;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Web;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTest.csv")
public class InformacoesUsuarioTest {

    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Web.createFirefox();



        //Identificando o formulário de Login
        WebElement formulariosigninbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name login que está dentro do formulário de id "signinbox o texto "julio0001"
        formulariosigninbox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo  com name password que está dentro do formulário de id "signinbox o texto "123456"
        formulariosigninbox.findElement(By.name("password")).sendKeys("123456");
        //Clicar no link com o texto SIGN IN
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();


    }


    @Ignore("Com validação")
    public void testEncontrarUmaInformação() {

        //Clicar no link que possui o texto Sign in
        WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        linkSignIn.click();

        //Identificando o formulário de Login
        WebElement formulariosigninbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name login que está dentro do formulário de id "signinbox o texto "julio0001"
        formulariosigninbox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo  com name password que está dentro do formulário de id "signinbox o texto "123456"
        formulariosigninbox.findElement(By.name("password")).sendKeys("123456");
        //Clicar no link com o texto SIGN IN
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo") String tipo, @Param(name = "contato") String contato, @Param(name = "mensagem") String mensagemEsperada) {

        //Clicar no botão através do xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar o poupup onde está o formulário de id addmoredata
        WebElement poupAddMoreData = navegador.findElement(By.id("addmoredata"));
        //Na combo de name "type" escolher a opção Phone
        WebElement campoType = poupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);
        // No campo de name "contact" digitar "+5511999991111"
        poupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
        //Clicar no link de text "SAVE"
        poupAddMoreData.findElement(By.linkText("SAVE")).click();
        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
    }

    @Ignore
    public void removerUmContatoDeUmUsuario() {
        //Clicar no elemento pelo seu xpath //span[text()="+5511999991111"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5511999991111\"]/following-sibling::a")).click();
        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();
        //Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);
        String localarquivo = "/home/maiko/Imagens/automatizacao" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, localarquivo);
        // Aguardar até 10 segundo para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        // Clicar no link com texto "logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        //Fechar o navegador
        navegador.quit();
    }
}
