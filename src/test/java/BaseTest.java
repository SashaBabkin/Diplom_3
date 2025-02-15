import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;
    String browser = "chrome";
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void prepare() {
        if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\IdeaPad 5 Pro\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe\"");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
           WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
        }

        driver.get(URL);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
