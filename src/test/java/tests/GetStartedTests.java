package tests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase
{
    @Test
    @Step("This test is iOS only. Starting testPassThroughWelcome")
    @DisplayName("Complete onboarding screen")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isWeb()))
        {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWaysToExplore();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitAddLanguagesLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitLearnMoreAboutCollectedDataLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
