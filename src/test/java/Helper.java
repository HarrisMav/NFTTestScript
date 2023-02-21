import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

/**
 * Helper method is used to find Venly specific elements and click them. If no Element is found the method will refrain you from using it.
 */
public class Helper {
    /**
     * Finds all venly-button elements and then searches them via link text to click. If more than expected return test is force failed
     * if none are found it also fails due to Selenium error.
     * @param driver common driver that is used
     * @param linkTextLocator link text of the button
     */
    public static void clickButtonByLinkText(final WebDriver driver, final String linkTextLocator){
        final List<WebElement> elementsWithSameLinkText = driver.findElements(By.tagName("venly-button"))
                .stream()
                .filter(venlyButton -> venlyButton.getText().equals(linkTextLocator))
                .toList();

        if (elementsWithSameLinkText.size() > 1) Assert.fail("The locator found an element with duplicate link text");

        elementsWithSameLinkText.get(0).click();
    }

    public static void switchHandles(final WebDriver driver, final Set<String> handles){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }
}
