package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebNavigationUI extends NavigationUI {
    static {
        SAVED_LISTING = "css:a[data-event-name='menu.watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }
    public WebNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
