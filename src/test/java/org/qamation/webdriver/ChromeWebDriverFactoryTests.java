package org.qamation.webdriver;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qamation.commons.config.Config;
import org.qamation.commons.webdriver.ChromeDriverFactory;

import java.time.Duration;
import java.util.Objects;

public class ChromeWebDriverFactoryTests {
    private WebDriver driver = null;
    private By q = By.cssSelector("input[name='q']");

    @BeforeClass
    public static void setUp() {
        System.setProperty("env", "test");
    }

    @After
    public void tearDown() {
        if (Objects.nonNull(driver)) driver.quit();
    }

    @Test
    public void createLocalChromeDriver() {
        driver = ChromeDriverFactory.createLocalChromeDriver();
        testDriverOnGoogle(driver);

    }

    @Test
    public void createLocalChromeDriverUsingExecPath() {
        driver = ChromeDriverFactory.createLocalChromeDriver(Config.getConfig().getChromeDriverPath());
        testDriverOnGoogle(driver);
    }

    @Test
    public void createLocalChromeDriverUsingOptions() {
        ChromeOptions options = new ChromeOptions();
        driver = ChromeDriverFactory.createLocalChromeDriver(options);
        testDriverOnGoogle(driver);
    }

    @Test
    public void createLocalChromeDriverUsingExecPathAndOptions() {
        ChromeOptions options = ChromeDriverFactory.getChromeOptions();
        driver = ChromeDriverFactory.createLocalChromeDriver(Config.getConfig().getChromeDriverPath(), options);
        testDriverOnGoogle(driver);
    }

    private void waitForQ(WebDriver driver) {
        Duration duration = Duration.ofSeconds(Config.getConfig().getPageLoadTimeOutMills() / 1000);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.elementToBeClickable(q));
    }

    private void testDriverOnGoogle(WebDriver driver) {
        driver.get("http://google.com");
        driver.manage().window().fullscreen();
        waitForQ(driver);
    }


}
