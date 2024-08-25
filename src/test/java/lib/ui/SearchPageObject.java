package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL,
    SEARCH_RESULT_ARTICLES,
    SEARCH_EMPTY_RESULT,
    SEARCH_INPUT_PAGE,
    SEARCH_PAGE_SAVE_FOR_LATER_BUTTON;
    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /*TEMPLATES METHODS */
    public static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    public static String getResultSearchElementTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }


    /*TEMPLATES METHODS */

    public void initSearchInput()

    {
        this.waitForElementAndClick((SEARCH_INIT_ELEMENT), "Can't find and click search element", 10);
        this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Can't find search element", 10);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON),"Can't find Cancel button", 10);

    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON),"Cancel button is not hidden", 10);

    }

    public void clickCancelSearch()
    {
        waitForElementAndClick((SEARCH_CANCEL_BUTTON),"Can't find and click search cancel button", 10);
    }


    public String typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys((SEARCH_INPUT), search_line,10);

        return search_line;
    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath), "Can't find search result with substring " + substring, 10);
    }
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick((search_result_xpath), "Can't find and click search result with substring " + substring, 10);
    }
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                (SEARCH_RESULT_ARTICLES),
                "Can't find search results ",
                10
        );
        return this.getAmountOfElements((SEARCH_RESULT_ARTICLES));
    }
    public void waitForEmptySearch()
    {
        this.waitForElementPresent((SEARCH_EMPTY_RESULT), "Can't find empty search", 10);
    }
    public void assertEmptySearchResult()
    {
        this.assertElementNotFound((SEARCH_EMPTY_RESULT), "No search results are expected", 10);
    }
    public void waitForSearchInput()
    {
        this.waitForElementPresent((SEARCH_INPUT_PAGE), "Can't find empty search field", 10);
    }
    public void assertEmptySearchInput()
    {
        this.waitForElementPresent((SEARCH_INPUT_PAGE), "Search field is expected to be displayed", 10);

    }
    public void waitForSearchResultTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementTitle(substring);
        this.waitForElementPresent((search_result_xpath), "Can't find search result with substring " + substring, 10);
    }
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        this.waitForSearchResultTitle(title);
        this.waitForSearchResult(description);
    }
    public void saveArticleForLaterSearchPage() {
        this.waitForElementAndClick(SEARCH_PAGE_SAVE_FOR_LATER_BUTTON, "Can't find save for later button", 10);
    }
}
