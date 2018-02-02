package ru.levelup.qa.at.test.work.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.levelup.qa.at.test.work.page.objects.EbayHomePage;
import ru.levelup.qa.at.test.work.services.webdriver.DriverParams;

import java.util.concurrent.TimeUnit;

public class BasicTest {

    protected WebDriver driver;

    protected EbayHomePage ebayHomePage;

    @BeforeClass
    public void setUpDriver() {
        ChromeDriverManager.getInstance().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(DriverParams.TIMEOUT_MS, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(DriverParams.PAGE_LOAD_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        ebayHomePage = new EbayHomePage(driver);
        ebayHomePage.open();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
