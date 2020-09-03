package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

public class Web {
    public static final String AUTOMATE_USERNAME = "maikodeizepi1";
    public static final String AUTOMATE_ACCESS_KEY = "2q6VqKUxXbnXfbmWyGy6";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static WebDriver createFirefox() {
        //Abrindo o Navegador
        System.setProperty("webdriver.gecko.driver", "/home/maiko/geckodriver");
        WebDriver navegador = new FirefoxDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegando para o site
        navegador.get("http://www.juliodelima.com.br/taskit");
        return navegador;
    }

    public static WebDriver createBrowserStack(){
        // Sample test in Java to run Automate session.
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "84.0");
        caps.setCapability("os", "Windows");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        navegador.get("https://www.juliodelima.com.br/taskit");
                WebElement element = navegador.findElement(By.name("q"));
                element.sendKeys("BrowserStack");
                element.submit();
                System.out.println(navegador.getTitle());
                navegador.quit();

        return navegador;
    }
}
