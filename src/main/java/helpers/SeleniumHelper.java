package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHelper {

    private static WebDriver driver;
    private static WebDriverWait wait;

    /**
     * function to initialize selenium helper
     * @param waitTimeoutDurationInSeconds duration of seconds to wait
     */
    public static void initializeSeleniumHelper(Long waitTimeoutDurationInSeconds, boolean headless) {
        WebDriverManager.chromedriver().setup();
        if (headless) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        else {
            driver = new ChromeDriver();
        }
        initializeWait(waitTimeoutDurationInSeconds);
    }

    /**
     * function to quit web driver
     */
    public static void quitWebDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }

    /**
     * function to initialize instance of web driver wait
     * @param waitTimeoutDurationInSeconds duration of seconds to wait
     */
    public static void initializeWait(Long waitTimeoutDurationInSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeoutDurationInSeconds));
    }

    /**
     * function to get web-page opened on browser window
     * @param url url to navigate
     */
    public static void openWebPageFromURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    /**
     * function to click on a web element
     * @param webElement: web element to click
     */
    public static void clickOnWebElement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * function to get a single view on the video
     * @param videoURL URL of the YouTube Video
     */
    public static void getView(String videoURL) {
        try {
            openWebPageFromURL(videoURL);
            String muteButtonXPATH = ".//button[@class='ytp-mute-button ytp-button']";
            clickOnWebElement(driver.findElement(By.xpath(muteButtonXPATH)));
            String playPauseButtonXPATH = ".//button[@class='ytp-play-button ytp-button']";
            clickOnWebElement(driver.findElement(By.xpath(playPauseButtonXPATH)));
            while (!driver.findElement(By.xpath(playPauseButtonXPATH)).getAttribute("title").equals("Replay")) {
                if (driver.findElement(By.xpath(playPauseButtonXPATH)).getAttribute("title").equalsIgnoreCase("Play (k)")) {
                    clickOnWebElement(driver.findElement(By.xpath(playPauseButtonXPATH)));
                }
            }
            if (driver.findElement(By.xpath(playPauseButtonXPATH)).getAttribute("title").equals("Replay")) {
                quitWebDriver();
            }
        }
        catch (WebDriverException e) {
            getView(videoURL);
        }
    }
}