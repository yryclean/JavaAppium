package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.OnboardingPageObject;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String WEB_DRIVER_URL = "https://en.m.wikipedia.org/";


    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiPageForMobileWeb();
        this.skipWelcome();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() throws Exception {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            driver.quit();
        } else {
            driver.close();
            driver.quit();
        }
    }
    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Move app to background mode, Method backgroundApp() does nothing for platform Web")
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Open wiki page on Web, Method openWikiPageForMobileWeb() does nothing for platform iOS and Android")
    protected void openWikiPageForMobileWeb()
    {
        if (Platform.getInstance().isWeb()) {
            driver.get(WEB_DRIVER_URL);
        } else {
            System.out.println("Method openWikiPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Skip onboarding screen on iOS and Android. Web is not supported")
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
    public void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties properties = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
            properties.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem writing allure properties file");
            e.printStackTrace();
        }
    }
}