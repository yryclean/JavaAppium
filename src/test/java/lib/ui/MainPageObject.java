package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static lib.ui.MySavedListsPageObject.SWIPE_DELETE_BUTTON;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public void tryClickElementWithAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 1);

                }
            }
            ++current_attempts;
        }
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, value, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes)
                waitForElementPresent(locator, "Cannot find element by swipe. \n" + error_message, 0);
            return;
        }
        scrollWebPage();
        ++already_swiped;

    }

    public void scrollTillElementAppears(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            scrollPageDown();
            ++already_swiped;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Can't find element by locator", 15).getLocation().getY();
        if (Platform.getInstance().isWeb()) {
            JavascriptExecutor js = driver;
            Object js_result = js.executeScript("return window.pageYOffset");
            element_location_by_y = Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    public void testSwipe(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);
            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = (int) (size.getWidth() * 0.25);
            int endY = startY;
            int offset_x = (-1 * element.getSize().getWidth());
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(200)))
                    .addAction(finger1.createPointerMove(ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
        } else {
            System.out.println("Method testSwipe() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void simpleSwipe(String locator) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator, "Can't find element to swipe", 10);

            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = (int) (size.getWidth() * 0.25);
            int endY = startY;
            int offset_x = (-1 * element.getSize().getWidth());
            int offset_y = (-1 * element.getSize().getHeight());

            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(2000)))
                    .addAction(finger1.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endY, offset_x))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));

        } else {
            System.out.println("Method simpleSwipe() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollPageDown() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = size.getHeight() / 2;
            int endX = startX;
            int endY = (int) (size.getHeight() * 0.1);
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, ofMillis(5)))
                    .addAction(finger1.createPointerMove(ofMillis(20), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
        } else {
            System.out.println("Method scrollPageDown() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void assertElementNotFound(String locator, String error_message, long timeoutInSeconds) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + locator + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetText(String locator, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(locator, error_message, timeOut);
        return element.getText();
    }

    public By getLocatorByString(String locator_with_type) {
        String[] explode_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explode_locator[0];
        String locator = explode_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("name")) {
            return By.name(locator);
        } else if (by_type.equals("css")) {
                return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator Locator: " + locator_with_type);
        }
    }
        public void swipeToTheLeft(String locator) {
        WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);

            JavascriptExecutor js = driver;
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "left");
            params.put("element", ((RemoteWebElement) element).getId());
            js.executeScript("mobile: swipe", params);
            this.waitForElementPresent(SWIPE_DELETE_BUTTON,"Can't find Delete button");
        }
        public void scrollWebPage() {
        if (Platform.getInstance().isWeb()) {
            JavascriptExecutor js = driver;
            js.executeScript("window.scrollBy(0, 250)");
            } else {
            System.out.println("Method scrollWebPage() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }
        public void scrollWebPageTillElementVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPage();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }
    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name +"_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        }catch (Exception e) {
            System.out.println("Can't take screenshot. Error: " + e.getMessage());
        }
        return path;
    }
    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            System.out.println("Can't get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }

}

