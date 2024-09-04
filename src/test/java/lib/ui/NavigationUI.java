package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
           SAVED_LISTING,
            OPEN_NAVIGATION;
@Step("Opening navigation menu on Web. Method openNavigation() does nothing for platform iOS and Android")
    public void openNavigation() {
        if (Platform.getInstance().isWeb()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Can't open navigation menu", 10);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }
    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("Clicking on saved lists button")
        public void clickSavedList() {
            if (Platform.getInstance().isWeb()) {
                this.tryClickElementWithAttempts(
                        SAVED_LISTING,
                        "Can't find my saved lists button",
                        10
                );
                driver.navigate().refresh();

            } else {
                this.waitForElementAndClick(
                        (SAVED_LISTING),
                        "Cannot find Saved button",
                        10
                );
                driver.navigate().refresh();

            }
        }
}
