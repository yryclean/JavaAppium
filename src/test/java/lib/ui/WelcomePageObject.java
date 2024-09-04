package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
            STEP_LEARN_MORE_LINK = "name:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_LANGUAGES_LINK = "name:Add or edit preferred languages",
            STEP_LEARN_MORE_COLLECT_DATA_LINK = "name:Learn more about data collected",
            NEXT_BUTTON = "name:Next",
            GET_STARTED_BUTTON = "name:Get started",
            SKIP_BUTTON = "name:Skip";



    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("Waiting for Learn More Link")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent((STEP_LEARN_MORE_LINK), "Can't find Learn more link", 30);
    }
    @Step("Clicking on Next button")
    public void clickNextButton()
    {
        this.waitForElementAndClick((NEXT_BUTTON), "Can't find and click Next button", 10);
    }
    @Step("Waiting for New Ways to Explore text to be displayed")
    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent((STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Can't find New ways to explore text", 10);
    }
    @Step("Waiting for Add language link")
    public void waitAddLanguagesLink()
    {
        this.waitForElementPresent((STEP_ADD_LANGUAGES_LINK), "Can't find Add or edit preferred languages link", 10);
    }
    @Step("Waiting for Learn more about collected data link")
    public void waitLearnMoreAboutCollectedDataLink()
    {
        this.waitForElementPresent((STEP_LEARN_MORE_COLLECT_DATA_LINK), "Can't find Learn more about data collected link", 10);
    }
    @Step("Clicking on Get Started button")
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick((GET_STARTED_BUTTON), "Can't find Get started button", 10);
    }
    @Step("Clicking on Skip button")
    public void clickSkip()
    {
        this.waitForElementAndClick((SKIP_BUTTON), "Can't find and click skip button", 5);

    }
}

