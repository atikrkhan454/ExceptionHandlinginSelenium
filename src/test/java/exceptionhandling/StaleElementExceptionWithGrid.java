package exceptionhandling;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class StaleElementExceptionWithGrid {

    WebDriver driver;

    @BeforeTest
    void openWeb() throws MalformedURLException, InterruptedException {

        String nodeURL="http://172.20.10.5:4444";
        DesiredCapabilities cap= new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URL(nodeURL),cap);
        driver.get("https://www.atmecs.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

    }

    @Test
    void navigate() throws InterruptedException {

        WebElement link= driver.findElement(By.xpath("//*[@id='menu-item-29']/a"));
        link.click();

        driver.navigate().back();

        Thread.sleep(5000);

        try {
            link.click();
        }
        catch (StaleElementReferenceException e){
            link= driver.findElement(By.xpath("//*[@id='menu-item-29']/a"));
            link.click();
        }

        finally {
            driver.quit();
        }

    }
}
