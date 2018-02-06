package ru.levelup.qa.at.test.work.page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchPage extends AbstractPage {

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@id,'_nkw')]")
    private WebElement searchString;

    @FindBy(xpath = "//input[contains(@id,'_ex_kw')]")
    private WebElement ignoredSearchString;

    @FindBy(xpath = "//input[contains(@id,'LH_TitleDesc')]")
    private WebElement boxSearchInTitiles;

    @FindBy(xpath = "//input[contains(@id,'LH_Sold')]")
    private WebElement boxSearchInSold;

    @FindBy(xpath = "//input[contains(@id,'LH_Auction')]")
    private WebElement boxSearchInAuction;

    @FindBy(xpath = "//input[contains(@id,'LH_ItemConditionNS')]")
    private WebElement boxSearchInConditionNS;

    @FindBy(xpath = "//button[contains(@class,'btn btn-prim')]")
    private WebElement buttonSearch;


    public static class Builder {
        private String searchString = "";
        private String ignoredSearchString = "";
        private boolean boxSearchInTitiles = false;
        private boolean boxSearchInSold = false;
        private boolean boxSearchInAuction = false;
        private boolean boxSearchInConditionNS = false;

        public Builder(){

        }

        public Builder setSearchString(String searchString) {
            this.searchString = searchString;
            return this;
        }

        public Builder setIgnoredSearchString(String ignoredSearchString) {
            this.ignoredSearchString = ignoredSearchString;
            return this;
        }

        public Builder setBoxSearchInTitiles(boolean boxSearchInTitiles) {
            this.boxSearchInTitiles = boxSearchInTitiles;
            return this;
        }

        public Builder setBoxSearchInSold(boolean boxSearchInSold) {
            this.boxSearchInSold = boxSearchInSold;
            return this;
        }

        public Builder setBoxSearchInAuction(boolean boxSearchInAuction) {
            this.boxSearchInAuction = boxSearchInAuction;
            return this;
        }

        public Builder setBoxSearchInConditionNS(boolean boxSearchInConditionNS) {
            this.boxSearchInConditionNS = boxSearchInConditionNS;
            return this;
        }

        public void build(AdvancedSearchPage page) {
            page.Build(this);
        }
    }

    public ResultPage Build(Builder builder) {

        searchString.sendKeys(builder.searchString);
        ignoredSearchString.sendKeys(builder.ignoredSearchString);
        if (builder.boxSearchInTitiles)
            SelectCheckBox(boxSearchInTitiles);
        if (builder.boxSearchInSold)
            SelectCheckBox(boxSearchInSold);
        if (builder.boxSearchInAuction)
            SelectCheckBox(boxSearchInAuction);
        if (builder.boxSearchInConditionNS)
            SelectCheckBox(boxSearchInConditionNS);

        buttonSearch.click();

        return new ResultPage(driver);
    }

    private void SelectCheckBox(WebElement element) {
        if ( !element.isSelected() )
        {
            element.click();
        }
    }
}
