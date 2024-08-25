package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
                SEARCH_INPUT = "xpath://*[@text='Search Wikipedia']";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description']//*[contains(text(), '{SUBSTRING}')]";
                SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']//*[contains(text(), '{SUBSTRING}')";
                SEARCH_RESULT_ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']";
                SEARCH_EMPTY_RESULT = "xpath://*[@text='No results']";
                SEARCH_INPUT_PAGE = "id:org.wikipedia:id/search_empty_message";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
       super(driver);
    }


}
