package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ARTICLES = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT = "id:No results found";
        SEARCH_INPUT_PAGE = "id:Search Wikipedia";
        SEARCH_PAGE_SAVE_FOR_LATER_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
