package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://p[contains(text(), 'Keep')]",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "xpath://*[@id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting and clicking on login button")
    public void clickAuthButton()  {
        this.waitForElementPresent(LOGIN_BUTTON, "Can't find Login button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Can't click Login button", 10);
    }

    @Step("Entering '{login}' '{password}'")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, 10);

    }
    @Step("Clicking on Submit button after entering login and password data")
    public void submitAuth() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can't find and click Submit button",10);
    }
}
