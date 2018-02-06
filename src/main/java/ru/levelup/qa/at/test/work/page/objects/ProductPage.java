package ru.levelup.qa.at.test.work.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.qa.at.test.work.page.elements.ResultsSearch;

import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//span[contains(@id,'convbidPrice')]")
    private WebElement priceInRub;

    @FindBy(xpath = "//select[contains(@class,'msku-sel')]")
    private List<WebElement> selectChecks;


    @FindBy(xpath = "//a[contains(@id,'isCartBtn_btn')]")
    private WebElement buttonAddToCart;



    @FindBy(xpath = "//span[contains(@id,'prcIsum')]")
    private WebElement price;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Double getPriceInRub() {
        String price = priceInRub.getText();
        price = price.replaceAll(" ", "");

        boolean res = true;
        while (res) {
            if (isDouble(price) == true) {
                res = false;
            }
            else {
                price = price.substring(0, price.length() - 1);
            }
        }
        return Double.parseDouble(price);
    }

    public void buyProduct() {
        for (WebElement el: selectChecks) {
            el.findElement(By.id("msku-opt-0")).click();
        }

        buttonAddToCart.click();
    }


}
