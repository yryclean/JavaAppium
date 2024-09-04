package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    @DisplayName("Search for article")
    @Description("Searching for article and waiting for search results")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    @DisplayName("Cancel search")
    @Description("Typing text in search line and tapping cancel button")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    @DisplayName("Search for article and wait for some results")
    @Description("Searching for article and confirm that search results are not empty")
    @Step("Starting testOfAmountNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testOfAmountNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_input = "Linkin park discography";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_input);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "Amount of search results is less than expected",
                amount_of_search_results > 0
        );
    }
    @Test
    @DisplayName("Search for non-existing article")
    @Description("Searching for non-existing article and confirm that there is no search results")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("rfeurhguegerhughure");
        SearchPageObject.waitForEmptySearch();
        SearchPageObject.assertEmptySearchResult();
    }
    @Test
    @DisplayName("Search for article by title and description")
    @Description("Searching for article by title and description, and confirm there's 3 matching results")
    @Step("Starting testSearchByArticleAndTitle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchByArticleAndTitle()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island in Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "High-level programming language");
    }
}
