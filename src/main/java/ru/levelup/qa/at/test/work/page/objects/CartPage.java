package ru.levelup.qa.at.test.work.page.objects;

import org.openqa.selenium.WebDriver;

public class CartPage extends AbstractPage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPage() {
        boolean result = driver.getTitle().contains("Ваша корзина");
        return result;
    }
}
