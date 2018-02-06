package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PopCategoryColumn  extends AbstractElement {

    @FindBy(xpath = "//a[contains(@class,'title extended-links')]")
    private List<WebElement> columnPopCat;
    private SubPopCategoryColumn subPopCategoryColumn;

    public PopCategoryColumn(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getColumnPopCat() {
        return columnPopCat;
    }

    public SubPopCategoryColumn getSubPopCategoryColumn() {
        return subPopCategoryColumn = new SubPopCategoryColumn(driver);
    }
}
