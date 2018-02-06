package ru.levelup.qa.at.test.work.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.qa.at.test.work.page.elements.CategoryMenu;
import ru.levelup.qa.at.test.work.page.elements.PopCategoryColumn;
import ru.levelup.qa.at.test.work.page.elements.SubPopCategoryColumn;
import ru.levelup.qa.at.test.work.page.objects.*;
import ru.levelup.qa.at.test.work.services.webdriver.DriverParams;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasicTest {

    protected WebDriver driver;

    protected EbayHomePage ebayHomePage;

    private static final String searchStringForCostOfGoodsTest = "iPhone";
    private static final Double accessWindow = 1000.0;
    private static final String searchStringSecondMenuTest = "Гитары";
    private static final String searchStringCategoryMenuTest = "Бас-гитары";
    private static final String orderStringCategoryMenuTest = "//a[contains(.,'по цене + доставке: по возрастанию')]";

    private static final String searchStringBuyingTest = "Компьютеры и планшеты";
    private static final String searchPopCatStringBuyingTest = "Аксессуары для ноутбуков и стационарных ПК";
    private static final String searchSubPopCatStringBuyingTest = "Гарнитуры";


    private static final String nameRegistrationTest = "test";
    private static final String lastNameRegistrationTest = "test";
    private static final String emailRegistrationTest = "test@gmail.com";
    private static final String passRegistrationTest = "sdaewe312dsafd3";


    private static final String searchStringExtendSearchTest = "бас";



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

    @Test
    public void costOfGoodsTest() {
        ebayHomePage.getHeader().sendKeysToSearchTextBox(searchStringForCostOfGoodsTest);
        ResultPage resultPage = ebayHomePage.getHeader().clickSearchButton();
        Double minPrice = resultPage.getResultsSearch().getPrice(0);
        ProductPage productPage = resultPage.getResultsSearch().selectResult(0);
        Double productPrice = productPage.getPriceInRub();
        Double diff = productPage.getPriceInRub() - minPrice;
        Assert.assertTrue(diff < accessWindow);
    }

    @Test
    public void checkingSorting() {
        List<WebElement> secondMenu = ebayHomePage.getHeader().getCategoryMenu();

        FindElementByName(secondMenu, searchStringSecondMenuTest);


        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("cat-link")));

        ResultPage resultPage = new ResultPage(driver);
        List<WebElement> categoryColumn = resultPage.getCategoryColumn().getColumnCat();
        FindElementByName(categoryColumn, searchStringCategoryMenuTest);

        resultPage.getSortingMenu().SelectSortingBy(orderStringCategoryMenuTest);

        for (int i = 0; i < 5; i++) {
            Double var1 = resultPage.getResultsSearch().getFullPrice(i);
            Double var2 = resultPage.getResultsSearch().getFullPrice(i+1);
            boolean result = resultPage.getResultsSearch().getFullPrice(i) <
                    resultPage.getResultsSearch().getFullPrice(i + 1);
            Assert.assertTrue(result, "Ошибка в порядре сортировки на элементе " + Integer.toString(i));
        }
    }

    @Test
    public void BuyingTest () {

        List<WebElement> secondMenu = ebayHomePage.getHeader().getCategoryMenu();

        FindElementByName(secondMenu, searchStringBuyingTest);

        ResultPage resultPage = new ResultPage(driver);
        PopCategoryColumn popCategoryColumn = resultPage.getPopCategoryColumn();
        FindElementByName(popCategoryColumn.getColumnPopCat(), searchPopCatStringBuyingTest);
        SubPopCategoryColumn subPopCategoryColumn = popCategoryColumn.getSubPopCategoryColumn();
        FindElementByName(subPopCategoryColumn.getColumnSubPopCat(), searchSubPopCatStringBuyingTest);

        resultPage.getResultsSearch().getLinks().get(0).click();
        ProductPage productPage = new ProductPage(driver);
        productPage.buyProduct();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartPage(), "Покупка не попала в корзину");
    }

    @Test
    public void registrationTest() {
        ebayHomePage.getHeader().getRegistrationButton().click();
        RegistationPage registationPage = new RegistationPage(driver);
        registationPage.registerUser(nameRegistrationTest, lastNameRegistrationTest, emailRegistrationTest, passRegistrationTest);

        Assert.assertTrue(registationPage.isError(), "Учетна запись свободна");
    }

    @Test
    public void linkTest() {
        List<WebElement>elements = ebayHomePage.getHeader().getCategoryMenu();
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            //List<WebElement>elements = ebayHomePage.getHeader().getCategoryMenu();
            if (i!=0)
                ebayHomePage.getHeader().getCategoryMenu();
            elements.get(i).click();
            driver.navigate().back();
        }
    }

    @Test
    public void advancedSearchTest(){
        AdvancedSearchPage advancedSearchPage = ebayHomePage.getHeader().clickAnvancedSearchButton();
        ResultPage resultPage = advancedSearchPage.Build(new AdvancedSearchPage.Builder().
                setBoxSearchInSold(true).
                setBoxSearchInConditionNS(true).
                setBoxSearchInAuction(true).
                setBoxSearchInTitiles(true).
                setIgnoredSearchString(" ").
                setSearchString(searchStringExtendSearchTest));

        for (int i = 0; i < 3; i++)
        {
            Assert.assertTrue(resultPage.getResultsSearch().getLinks().get(i).getText().contains(searchStringExtendSearchTest));
        }
    }

    private void  FindElementByName(List<WebElement> elements, String name) {
        boolean priznak = false;

        for (WebElement element: elements ) {
            if (element.getText().contains(name)) {
                element.click();
                priznak = true;
                break;
            }
        }
        Assert.assertTrue(priznak, "Нет такого элемента");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
