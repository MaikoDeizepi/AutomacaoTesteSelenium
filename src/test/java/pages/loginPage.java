package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends basePage{


    public loginPage(WebDriver navegador) {
        super(navegador);
    }

    public loginFormPage clicarSignin(){
        navegador.findElement(By.linkText("Sign in")).click();
        return new loginFormPage(navegador);
    }
}
