package tests;
import io.appium.java_client.touch.WaitOptions;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MySavedListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;


public class ArticleTests extends CoreTestCase
{
    private static final String name_of_folder = "My new folder";
    private static final String
                login = "yryclean",
                password = "Chistik123";
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    @Test
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
    public void testSaveFirstArticleToMyList() {
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
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
            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
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
        assertEquals(
                "Title changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Title changed after second rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
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
    public void testSaveTwoFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String name_of_folder = "My new folder";
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
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
            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        String title2 = SearchPageObject.typeSearchLine("CSS");
        SearchPageObject.clickByArticleWithSubstring("Style sheet language");
        ArticlePageObject.waitForArticleTitle(title2);
        assertEquals(
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


