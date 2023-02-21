import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.time.Duration;

@Test()
public class VenlyTest extends Hooks {
    @AfterSuite
    private static void teardown(){
        driver.quit();
    }
    @Test
    public void test(){
        driver.get("https://venly.market/home");
        final String winHandleBefore = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete"));
        Helper.clickButtonByLinkText(driver, "Login / Sign up");

        Helper.switchHandles(driver, driver.getWindowHandles());
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        driver.findElement(By.linkText("or Using Email")).click();
        driver.findElement(By.id("username")).sendKeys("haristsp1@gmail.com");
        driver.findElement(By.id("password")).sendKeys("1234Abcd!");
        driver.findElement(By.name("login")).click();

        driver.close();

        driver.switchTo().window(winHandleBefore);
        System.out.println();
    }
}
