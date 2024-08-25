package lib.ui.mobile_web;

import lib.ui.MySavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebMySavedListsPageObject extends MySavedListsPageObject {
    static {
        //h3[contains(text(), 'Java')]
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class, 'page-summary with-watchstar')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@title, '{TITLE}')]//a[2]";
    }
    public WebMySavedListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
