package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform
{
    private static Platform instance;
    private Platform() {}
    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else if (this.isWeb()) {
            return new ChromeDriver(this.getWebChromeOptions());
        } else {
            throw new Exception("Can't detect platform driver. Platform value " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isWeb() { return isPlatform(PLATFORM_MOBILE_WEB); }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "15.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wiki.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:deviceName", "iPhone 14 Pro");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getWebChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        Map<String, Object> clientHints = new HashMap<>();
        clientHints.put("platform", "Android");
        clientHints.put("mobile", true);
        mobileEmulation.put("clientHints", clientHints);
        ChromeOptions chromeOptions = new ChromeOptions(); chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation); WebDriver driver = new ChromeDriver(chromeOptions);
        return chromeOptions;
    }


    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);

    }

    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
