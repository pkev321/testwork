package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryColumn extends AbstractElement {

    @FindBy(xpath = "//div[contains(@class,'cat-link')]//a")
    private List<WebElement> columnCat;

    public CategoryColumn(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getColumnCat() {
        return columnCat;
    }
}
