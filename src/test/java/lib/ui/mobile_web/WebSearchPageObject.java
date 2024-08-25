package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:div.header-action";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://a[contains(@data-title, '{SUBSTRING}')]";
        SEARCH_RESULT_ARTICLES = "css:ul>li.page-summary";
        SEARCH_EMPTY_RESULT = "css:div.caption";
        SEARCH_INPUT_PAGE = "css:div.overlay.search-overlay.visible";
    }

    public WebSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
