package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OnboardingPageObject extends MainPageObject {

    private final static String
    SKIP_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";


    public OnboardingPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("Skipping onboarding screen")
    public void skipOnboarding()
    {
        this.waitForElementAndClick((SKIP_BUTTON), "Can't find and click skip button", 5);
    }
}
