package ru.levelup.qa.at.test.work.page.objects;

import org.openqa.selenium.WebDriver;
import ru.levelup.qa.at.test.work.page.elements.CategoryColumn;
import ru.levelup.qa.at.test.work.page.elements.PopCategoryColumn;
import ru.levelup.qa.at.test.work.page.elements.ResultsSearch;
import ru.levelup.qa.at.test.work.page.elements.SortingMenu;

public class ResultPage extends AbstractPage {

    private ResultsSearch resultsSearch;

    private CategoryColumn categoryColumn;
    private PopCategoryColumn popCategoryColumn;


    private SortingMenu sortingMenu;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public ResultsSearch getResultsSearch() {
        return resultsSearch = new ResultsSearch(driver);
    }

    public CategoryColumn getCategoryColumn() {
        return categoryColumn = new CategoryColumn(driver);
    }

    public PopCategoryColumn getPopCategoryColumn() {
        return popCategoryColumn = new PopCategoryColumn(driver);
    }

    public SortingMenu getSortingMenu() {
        return sortingMenu = new SortingMenu(driver);
    }
}
