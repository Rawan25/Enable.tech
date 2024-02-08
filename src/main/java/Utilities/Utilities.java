package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Utilities {
    private static final String testDataPath = "src/main/resources/TestData/";
    private static final String environmentPath = "src\\main\\resources\\environment.properties";

    public static void clickOnButton(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    public static void setData(WebDriver driver, By locator, String data){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }
    public static String getJsonData(String filename, String key){
        try {
            FileReader fileReader = new FileReader(testDataPath+filename+".json");
            JsonElement jsonElement =  JsonParser.parseReader(fileReader);
            return jsonElement.getAsJsonObject().get(key).getAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
    public static String getPropertyValue(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(environmentPath));
        return properties.getProperty(key);
    }
    public static Boolean isElementVisible(WebDriver driver, By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String getText(WebDriver driver,By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

}
