package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.levelup.qa.at.test.work.page.objects.ProductPage;

import java.util.List;

public class ResultsSearch extends AbstractElement {

    @FindBy(className = "sresult ")
    private List<WebElement> results;

    @FindBy(xpath ="//h3[contains(@class,'lvtitle')]//a")
    private List<WebElement> links;

    @FindBy(xpath ="//li[contains(@class,'lvprice')]")
    private List<WebElement> prices;

    @FindBy(xpath ="//li[contains(@class,'lvshipping')]")
    private List<WebElement> shippingPrices;



    public List<WebElement> getLinks() {
        return links;
    }

    public Double getPrice(int index) {
        String price = prices.get(index).getText();
        return StringToDouble(price);
    }

    public Double getShippingPrice(int index) {
        String price = shippingPrices.get(index).getText();
        return StringToDouble(price);
    }

    public Double getFullPrice(int index) {
        return getPrice(index) + getShippingPrice(index);
    }

    private Double StringToDouble(String text) {

        String price = text;
        price = price.replaceAll(" ", "");

        boolean res = true;
        while (res && price.length() > 0) {
            if (isDouble(price) == true) {
                res = false;
            }
            else {
                price = price.substring(0, price.length() - 1);
            }
        }
        if (price.length() == 0)
            return 0.0;

        return Double.parseDouble(price);
    }

    public ProductPage selectResult(int index) {
        links.get(0).click();
        return new ProductPage(driver);
    }

    private static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public ResultsSearch(WebDriver driver) {
        super(driver);
    }
}
