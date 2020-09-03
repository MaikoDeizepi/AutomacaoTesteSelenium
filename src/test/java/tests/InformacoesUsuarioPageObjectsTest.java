package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.loginPage;
import suporte.Web;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createFirefox();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "login")String login,@Param(name = "senha")String senha,@Param(name = "tipo")String tipo,
                                                             @Param(name = "contato")String contato,@Param(name = "mensagem")String mensagemEsperada
    ){
    String textoToast = new loginPage(navegador)
            .clicarSignin()
            .fazerLogin(login,senha)
            .clicarMe()
            .clicarAbaMoreDataAboutYou()
            .clicarBotaoAddMoreDataAboutYou()
            .adicionarContato(tipo,contato)
            .capturarTextoToast();

    assertEquals(mensagemEsperada, textoToast);

    }
    @Test
    public void enviarCurriculo(){

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
