package DriverManager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocal= new ThreadLocal<>();

    public static void setUpDriver(String browser){

        switch (browser.toLowerCase()){
            case "chrome":
                threadLocal.set(new ChromeDriver());
                break;
            case "firefox":
                threadLocal.set(new FirefoxDriver());
                break;
            default:
                threadLocal.set(new EdgeDriver());
        }
    }

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    public static void quitDriver(){
        getDriver().quit();
        threadLocal.remove();
    }
}


