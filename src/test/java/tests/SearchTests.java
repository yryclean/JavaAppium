package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testOfAmountNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_input = "Linkin park discography";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_input);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Amount of search results is less than expected",
                amount_of_search_results > 0
        );
    }
    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("rfeurhguegerhughure");
        SearchPageObject.waitForEmptySearch();
        SearchPageObject.assertEmptySearchResult();
    }
    @Test
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
