import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        driver.get("https://staging.venly.market/home");
        boolean testPass = false;
        final String winHandleBefore = driver.getWindowHandle();
        final String expectedErrorMessage = "You don't have enough credits to purchase this item. Buy credits and try again.";

        WaitHelper.waitForPageToLoad(driver);
        Helper.clickButtonByLinkText(driver, "Login / Sign up");
        Helper.switchHandles(driver);
        WaitHelper.waitForPageToLoad(driver);
        driver.findElement(By.linkText("or Using Email")).click();
        driver.findElement(By.id("username")).sendKeys("haristsp1@gmail.com");
        driver.findElement(By.id("password")).sendKeys("1234Abcd!");
        driver.findElement(By.name("login")).click();
        driver.switchTo().window(winHandleBefore);
        WaitHelper.waitForPageToLoad(driver);
        Helper.findNonAffordableItemUSDC(driver).findElement(By.tagName("venly-button")).click();

        for (final WebElement errorMessage : driver.findElements(By.className("funds-error"))){
            if (errorMessage.getText().equals(expectedErrorMessage)) {
                testPass = true;
                break;
            }
        }

        Assert.assertTrue(testPass, "The error message did not appear");
    }
}
