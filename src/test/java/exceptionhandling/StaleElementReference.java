package exceptionhandling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StaleElementReference {

    WebDriver driver;
    @BeforeTest
    void openWeb(){

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/login/");
    }

    @Test
    void exceptionHandling() throws InterruptedException {

        WebElement username=driver.findElement(By.id("email"));
        username.sendKeys("arkhan");
        WebElement password=driver.findElement(By.id("pass"));
        password.sendKeys("Atik@1234");

        driver.navigate().refresh();

        //username.sendKeys("arkhan");
       // password.sendKeys("Atik@1234");

        try {
            username.sendKeys("arkhan");
            password.sendKeys("Atik@1234");
        }
        catch (StaleElementReferenceException e){

            username=driver.findElement(By.id("email"));
            username.sendKeys("arkhan");
            password=driver.findElement(By.id("pass"));
            password.sendKeys("Atik@1234");

            System.out.println("Successfully Handle");
        }
         Thread.sleep(3000);
         driver.quit();

    }
}