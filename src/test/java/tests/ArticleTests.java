package tests;
import io.appium.java_client.touch.WaitOptions;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MySavedListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase
{
    private static final String name_of_folder = "My new folder";
    private static final String
                login = "yryclean",
                password = "Chistik123";
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title after open from search results")
    @Description("Search for article, then open and compare title from search results and opened article page")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
//        ArticlePageObject.takeScreenshot("article_page");
        Assert.assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    @DisplayName("Scroll opened article to the end")
    @Description("Open article, scroll to the bottom and find read more section")
    @Step("Starting testArticleSwipe")
    @Severity(value = SeverityLevel.NORMAL)
    public void testArticleSwipe() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.scrollTo();
        ArticlePageObject.findArticleReadMore();

    }
    @Test
    @DisplayName("Save and remove article by swipe")
    @Description("Add opened article to saved and remove from saved by swipe")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() {
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );
        if (Platform.getInstance().isAndroid() ||  Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToSaved();
        }
        if (Platform.getInstance().isWeb()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitAuth();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        } else {
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.openNavigation();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.closeSyncPopUp();
        } else {
            NavigationUI.clickSavedList();
        }
        MySavedListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    @DisplayName("Rotate screen with article")
    @Description("Open article, rotate screen and compare title")
    @Step("Starting testRotateSearchArticleBasic")
    @Severity(value = SeverityLevel.MINOR)
    public void testRotateSearchArticleBasic()
    {
        if (Platform.getInstance().isWeb()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Title changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Title changed after second rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @DisplayName("Background and foreground compare title")
    @Description("Search for article, move app to background and foreground and compare title")
    @Step("Starting testCheckArticleNameAfterBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckArticleNameAfterBackground()
    {
        if (Platform.getInstance().isWeb()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    @DisplayName("Save two articles, and remove one")
    @Description("Add two articles to save list, then remove one saved article from saved and open left one")
    @Step("Starting testSaveTwoFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String name_of_folder = "My new folder";
        String article_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );
        if (Platform.getInstance().isAndroid() ||  Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToSaved();
        }
        if (Platform.getInstance().isWeb()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitAuth();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        String title2 = SearchPageObject.typeSearchLine("CSS");
        SearchPageObject.clickByArticleWithSubstring("Style sheet language");
        ArticlePageObject.waitForArticleTitle(title2);
        Assert.assertEquals(
                "Unexpected title!",
                "CSS",
                title2
        );
        if (Platform.getInstance().isAndroid() ||  Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToSaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
            SearchPageObject.waitForCancelButtonToDisappear();
        } else if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        } else {
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.openNavigation();
        }
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.openFolderByName(name_of_folder);
            MySavedListsPageObject.swipeArticleToDelete(title2);
            MySavedListsPageObject.openArticleFromFolder(article_title);
            ArticlePageObject.waitForTitleElement();
        } else if (Platform.getInstance().isIOS()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.closeSyncPopUp();
            MySavedListsPageObject.swipeArticleToDelete(article_title);
            ArticlePageObject.waitForArticleTitle(title2);
            MySavedListsPageObject.openSavedArticle(title2);
        } else {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.swipeArticleToDelete(title2);
            MySavedListsPageObject.openSavedArticle(article_title);
            ArticlePageObject.waitForArticleNote(article_title);
        }
    }

}


