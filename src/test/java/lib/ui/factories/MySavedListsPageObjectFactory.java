package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MySavedListsPageObject;
import lib.ui.android.AndroidMySavedListsPageObject;
import lib.ui.ios.iOSMySavedListsPageObject;
import lib.ui.mobile_web.WebMySavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MySavedListsPageObjectFactory {

    public static MySavedListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMySavedListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new iOSMySavedListsPageObject(driver);
        } else {
            return new WebMySavedListsPageObject(driver);
        }
    }
}
