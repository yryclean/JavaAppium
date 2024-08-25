package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@text='Java (programming language)']";
        TITLE_BY_TPL = "xpath://*[@text='{TITLE}']";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action";
        INPUT_BAR = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
        OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        READ_MORE_ARTICLE_SECTION = "xpath://*[@resource-id='pcs-footer-container-readmore-heading']";
        EXISTING_LIST = "xpath://*[@text='My new folder']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}