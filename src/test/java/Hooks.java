import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for hooks, not meant to be initialised, main objective is to setup an instance of WebDriver.
 */
public abstract class Hooks {
    static WebDriver driver;
    static ChromeDriverService driverService;

    @BeforeClass
    public static void createChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(setupChromeOptions());
        driverService = ChromeDriverService.createDefaultService();
        driver = new ChromeDriver(driverService, chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeClass
    private static List<String> setupChromeOptions(){
        final List<String> chromeOptionsList = new ArrayList<>();
//        chromeOptionsList.add("--headless");
        chromeOptionsList.add("start-maximized");
//        chromeOptionsList.add("--incognito");

        return chromeOptionsList;
    }
}
