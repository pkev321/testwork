package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElements extends AbstractElement {

    @FindBy(id = "gh-shop-a")
    private WebElement shopByCategoryButton;

    @FindBy(id = "gh-ac")
    private WebElement searchTextBox;

    @FindBy(id = "gh-cat")
    private WebElement searchCategorySelect;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(id = "gh-as-a")
    private WebElement advancedButton;

    public HeaderElements(WebDriver driver) {
        super(driver);
    }

    public void sendKeysToSearchTextBox(final String searchText) {
        searchTextBox.clear();
        searchTextBox.sendKeys(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }


}
