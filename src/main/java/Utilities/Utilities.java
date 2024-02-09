package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static java.lang.Double.sum;

public class Utilities {
    private static final String testDataPath = "src/main/resources/TestData/";
    private static final String environmentPath = "src\\main\\resources\\environment.properties";
    private static final String screenshotsPath = "src\\main\\resources\\screenshots\\";


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

    public static double getSum( String firstPrice, String secondPrice){
        double firstProductPrice = removeDollarSign(firstPrice);
        double secondProductPrice = removeDollarSign(secondPrice);
        return sum(firstProductPrice,secondProductPrice);
        }

    public static double removeDollarSign(String price){
        if (price != null && price.contains("$")) {
            return Double.parseDouble(price.replace("$", ""));
        } else {
            // Return the original price if it's null or doesn't contain a dollar sign
            return Double.parseDouble(price);
        }
    }

    public static void takeScreenshot(WebDriver driver,String name) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File finalScreenshot = new File(screenshotsPath+name+getTimestamp()+".png");
        ImageIO.write(screenshot.getImage(),"png",finalScreenshot);
    }
    public static String getTimestamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd_h-m-ss a").format(new Date());
    }

}
