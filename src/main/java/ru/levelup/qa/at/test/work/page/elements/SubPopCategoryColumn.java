package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubPopCategoryColumn extends AbstractElement {

    @FindBy(xpath = "//li[contains(@data-node-id,'')]//a")
    private List<WebElement> columnSubPopCat;

    public SubPopCategoryColumn(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getColumnSubPopCat() {
        return columnSubPopCat;
    }



}
