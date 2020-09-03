package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginFormPage extends basePage{


    public loginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public loginFormPage tipoLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }
    public loginFormPage tipoPassword(String Password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(Password);
        return this;
    }
    public secretaPage clicarSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new secretaPage(navegador);
    }
    public secretaPage fazerLogin(String login, String Password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(Password);
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new secretaPage(navegador);
    }
}
