package org.qamation.webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.qamation.commons.webdriver.ChromeDriverFactory;

public class ChromeWebDriverFactoryTests {

    @Test
    public void createLocalChromeDriver() {
        WebDriver driver = ChromeDriverFactory.createLocalChromeDriver();
        driver.get("http://google.com");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }


}
