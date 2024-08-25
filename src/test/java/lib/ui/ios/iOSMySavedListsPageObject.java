package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MySavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMySavedListsPageObject extends MySavedListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_SYNC_POPUP = "id:Close";
        SWIPE_DELETE_BUTTON = "id:swipe action delete";
        ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        ALERT_UNSAVE_ARTICLE = "xpath://XCUIElementTypeAlert[@name='Unsave article?']";
        ALERT_UNSAVE_BUTTON = "id:Unsave";
    }
    public iOSMySavedListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
