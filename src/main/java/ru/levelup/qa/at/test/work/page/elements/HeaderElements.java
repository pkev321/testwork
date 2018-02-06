package ru.levelup.qa.at.test.work.page.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.qa.at.test.work.page.objects.AdvancedSearchPage;
import ru.levelup.qa.at.test.work.page.objects.ResultPage;

import java.util.List;

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

    @FindBy(xpath = "//span[contains(@id,'gh-ug-flex')]//a")
    private WebElement registrationButton;

    public HeaderElements(WebDriver driver) {
        super(driver);
    }

    public void sendKeysToSearchTextBox(final String searchText) {
        searchTextBox.clear();
        searchTextBox.sendKeys(searchText);
    }

    public ResultPage clickSearchButton() {
        searchButton.click();
        return new ResultPage(driver);
    }

    public List<WebElement> getCategoryMenu() {
        shopByCategoryButton.click();
        CategoryMenu categoryMenu = new CategoryMenu(driver);

        return categoryMenu.getSecondsCat();
    }

    public WebElement getRegistrationButton() {
        return registrationButton;
    }

    public AdvancedSearchPage clickAnvancedSearchButton() {
        advancedButton.click();
        return new AdvancedSearchPage(driver);
    }
}
