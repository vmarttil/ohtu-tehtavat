package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        // Alkuperäinen skenaario
        /*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit(); ''
        */

        // Login väärällä salasanalla
        /*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        */

        // Uuden käyttäjän luonti
        Random r = new Random();
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("ville" + r.nextInt(10000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("testi");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("testi");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        // Uloskirjautuminen sovelluksesta
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
