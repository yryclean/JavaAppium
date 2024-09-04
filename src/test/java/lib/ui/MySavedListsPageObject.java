package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MySavedListsPageObject extends MainPageObject {

    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    CLOSE_SYNC_POPUP,
    SWIPE_DELETE_BUTTON,
    ARTICLE_TITLE,
    ALERT_UNSAVE_ARTICLE,
    ALERT_UNSAVE_BUTTON,
    REMOVE_FROM_SAVED_BUTTON;



    private static String getFolderByXpathName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getTitleByXpathName(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    public MySavedListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Opening folder by name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder)
    {   String folder_name_xpath = getFolderByXpathName(name_of_folder);
        this.waitForElementAndClick(
                (folder_name_xpath),
                "Cannot find saved folder by name " + name_of_folder,
                5
        );
    }
    @Step("Waiting for article with title '{article_title}' to appear")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderByXpathName(article_title);
        this.waitForElementPresent(
                (article_xpath),
                "Cannot find saved article " + article_title,
                10
        );
    }

    @Step("Waiting for article with title '{article_title}' to disappear")
    public void waitForArticleTitleToDisappear(String article_title)
    {
        String article_xpath = getFolderByXpathName(article_title);
        this.waitForElementNotPresent(
                (article_xpath),
                "Saved article is still displayed " + article_title,
                10
        );
    }

    @Step("Swipe on article with title '{article_title}' to delete")
    public void swipeArticleToDelete(String article_title) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getTitleByXpathName(article_title);
            this.testSwipe(
                    (article_xpath),
                    "Cannot find saved article"
            );
            this.waitForArticleTitleToDisappear(article_xpath);
        } else if (Platform.getInstance().isIOS()) {
            String article_xpath = getTitleByXpathName(article_title);
            this.swipeToTheLeft(article_xpath);
            this.waitForElementPresent(SWIPE_DELETE_BUTTON, "Can't find delete button", 10);
            this.waitForElementAndClick(SWIPE_DELETE_BUTTON, "Can't click on delete button", 10);
            this.waitForElementPresent(ALERT_UNSAVE_ARTICLE, "Alert not displayed", 10);
            this.waitForElementAndClick(ALERT_UNSAVE_BUTTON, "Can't tap on the unsave alert button", 10);
            this.waitForElementNotPresent(article_xpath, "Article is not expected to be displayed", 10);

        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Can't find remove article button",
                    10
            );
        }
        driver.navigate().refresh();
        driver.navigate().refresh();

    }

    @Step("Opening folder by article title '{article_title}'")
    public void openArticleFromFolder(String article_title)
    {
        String article_xpath = getTitleByXpathName(article_title);
        this.waitForElementAndClick(
                (article_xpath),
                "Cannot open saved list from folder",
                5
        );
    }

    @Step("Closing sync pop-up")
    public void closeSyncPopUp()
    {
        this.waitForElementAndClick(CLOSE_SYNC_POPUP, "Can't find close pop-up button", 10);
    }

    @Step("Opening saved article with title '{article_title}'")
    public void openSavedArticle(String article_title) {
        String article_xpath = getTitleByXpathName(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot open saved list from folder",
                5
        );
    }
}
