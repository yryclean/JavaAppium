package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://span[contains(text(), 'Java (programming language)')]";
        TITLE_BY_TPL = "xpath://span[contains(text(), '{TITLE}')]";
        SAVE_BUTTON = "css:li#page-actions-watch";
        REMOVE_FROM_LIST = "css:[class='minerva-icon minerva-icon--unStar-progressive']";
        READ_MORE_ARTICLE_SECTION = "xpath://div[@class='minerva-footer-logo']";
        ARTICLE_NOTE = "xpath://*[contains(text(), '{TITLE}')]";
    }

    public WebArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
