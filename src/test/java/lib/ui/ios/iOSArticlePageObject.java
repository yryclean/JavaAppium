package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        TITLE_BY_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        SAVE_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        READ_MORE_ARTICLE_SECTION = "xpath://XCUIElementTypeStaticText[@name='READ MORE']";
        ADD_TO_LIST = "id:Add “Java (programming language)” to a reading list?";
        CREATE_LIST = "name:Create a new list";
        INPUT_BAR = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "name:Create reading list";

    }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}