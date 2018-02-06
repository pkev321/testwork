package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SortingMenu extends AbstractElement {

    @FindBy(xpath = "//div[contains(@class,'sort-menu-container')]")
    private WebElement sortButton;

    public SortingMenu(WebDriver driver) {
        super(driver);
    }

    public void SelectSortingBy(String order) {
        sortButton.click();
        sortButton.findElement(By.xpath(order)).click();
    }

}
