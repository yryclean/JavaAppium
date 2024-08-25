package tests;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase
{
    @Test
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
