package ru.levelup.qa.at.test.work.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistationPage extends AbstractPage {

    @FindBy(xpath = "//input[contains(@id,'firstname')]")
    private WebElement firstnameF;

    @FindBy(xpath = "//input[contains(@id,'lastname')]")
    private WebElement lastnameF;

    @FindBy(xpath = ".//*[@name='email']")
    private WebElement emailF;


    @FindBy(xpath = ".//*[@name='PASSWORD']")
    private WebElement passwordF;

    @FindBy(xpath = "//input[contains(@id,'ppaFormSbtBtn')]")
    private WebElement regButton;

    @FindBy(xpath = "//div[contains(@class,'txt-font')]//p")
    private WebElement messageString;


    public RegistationPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser (String name, String lastname, String email, String pass) {
        firstnameF.sendKeys(name);
        lastnameF.sendKeys(lastname);
        emailF.sendKeys(email);
        passwordF.sendKeys(pass);

        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(regButton));

        regButton.click();
    }

    public boolean isError() {
        boolean res = messageString.getText().contains("уже зарегистри");
        return res;
    }
}
