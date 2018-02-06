package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class CategoryMenu extends AbstractElement {
    @FindAll( {
        @FindBy(xpath = "//a[contains(@class,'scnd')]"),
        @FindBy(xpath = "//h3[contains(@class,'gh-sbc-parent')]//a")
    } )
    private List<WebElement> secondsCat;

    public CategoryMenu(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSecondsCat() {
        return secondsCat;
    }
}
