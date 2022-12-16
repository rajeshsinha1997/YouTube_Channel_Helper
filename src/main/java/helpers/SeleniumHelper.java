package helpers;

import constants.BrowserConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;

public class SeleniumHelper {

    private static WebDriver driver;
    private static WebDriverWait wait;

    /**
     * function to initialize selenium helper
     * @param waitTimeoutDurationInSeconds duration of seconds to wait
     */
    public static void initializeSeleniumHelper(Long waitTimeoutDurationInSeconds, BrowserConstants browser, boolean headless) {
        if (browser == BrowserConstants.Google_Chrome) {
            System.setProperty("webdriver.chrome.silentOutput", "true");
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--log-level=3");

            WebDriverManager.chromedriver().setup();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
            initializeWait(waitTimeoutDurationInSeconds);
        }
        else if (browser == BrowserConstants.Microsoft_Edge) {
            System.setProperty("webdriver.edge.silentOutput", "true");
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--log-level=3");

            WebDriverManager.edgedriver().setup();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver(options);
            initializeWait(waitTimeoutDurationInSeconds);
        }
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
     * function to wait for a web element to be visible
     * @param locator: locator of the web element
     */
    public static void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * function to click on a web element
     * @param webElement: web element to click
     */
    public static void clickOnWebElement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        if (webElement.isDisplayed()) webElement.click();
        else clickOnWebElement(webElement);
    }

    /**
     * function to get a single view on the video
     * @param videoURL URL of the YouTube Video
     */
    public static int getView(String videoURL, BrowserConstants browser, boolean headlessRequired, String videoID) {
        int viewCount = 0;
        try {
            SeleniumHelper.initializeSeleniumHelper(60L, browser, headlessRequired);
            openWebPageFromURL(videoURL);
            System.out.println("STARTING VIDEO PLAY AT: "+DateTimeHelper.getCurrentLocalDateTimeString());
            String muteButtonXPATH = ".//button[@class='ytp-mute-button ytp-button']";
            waitForElementToBeVisible(By.xpath(muteButtonXPATH));
            clickOnWebElement(driver.findElement(By.xpath(muteButtonXPATH)));
            String playPauseButtonXPATH = ".//button[@class='ytp-play-button ytp-button']";
            while (driver.getCurrentUrl().contains(videoID)) {
                waitForElementToBeVisible(By.xpath(playPauseButtonXPATH));
                if (driver.findElement(By.xpath(playPauseButtonXPATH)).getAttribute("title").equals("Play (k)")) {
                    clickOnWebElement(driver.findElement(By.xpath(playPauseButtonXPATH)));
                }
                else if (driver.findElement(By.xpath(playPauseButtonXPATH)).
                        getAttribute("title").contains("Replay")) {
                    System.out.println("FINISHED PLAYING VIDEO AT: " + DateTimeHelper.getCurrentLocalDateTimeString());
                    quitWebDriver();
                    return ++viewCount;
                }
            }
            if (!driver.getCurrentUrl().contains(videoID)) {
                System.out.println("FINISHED PLAYING VIDEO AT: " + DateTimeHelper.getCurrentLocalDateTimeString());
                quitWebDriver();
            }
            return ++viewCount;
        }
        catch (WebDriverException e) {
            System.out.println("ERROR:"+e.getLocalizedMessage());
            return getView(videoURL, browser, headlessRequired, videoID);
        }
    }
}