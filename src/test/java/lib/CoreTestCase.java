package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import junit.framework.TestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.WelcomePageObject;
import org.junit.BeforeClass;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.openWikiPageForMobileWeb();
        this.skipWelcome();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiPageForMobileWeb()
    {
        if (Platform.getInstance().isWeb()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("Method openWikiPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void skipWelcome() throws Exception {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        } else if (Platform.getInstance().isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
            OnboardingPageObject.skipOnboarding();
        }else if (Platform.getInstance().isWeb()) {
            System.out.println("Using Mobile Web version");
        } else {
            throw new Exception("Can't skip onboarding screen");
        }
    }
}