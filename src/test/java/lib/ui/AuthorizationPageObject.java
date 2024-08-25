package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://*[text()='Keep track of this page and all changes to it.']",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "xpath://*[@id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton()  {
        this.waitForElementPresent(LOGIN_BUTTON, "Can't find Login button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Can't click Login button", 10);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, 10);

    }
    public void submitAuth() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can't find and click Submit button",10);
    }
}
