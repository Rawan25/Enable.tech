package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utilities.Utilities;

public class LoginScreen {
    private WebDriver driver;
    private final By userNameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginScreen(WebDriver driver) {
        this.driver = driver;
    }

    public LoginScreen setUserName(String data) {
        Utilities.setData(driver, userNameInput, data);
        return this;
    }

    public LoginScreen setPassword(String data) {
        Utilities.setData(driver, passwordInput, data);
        return this;
    }

    public HomeScreen clickOnLoginButton() {
        Utilities.clickOnButton(driver, loginButton);
        return new HomeScreen(driver);
    }

    public Boolean assertHomeScreen(String expectedValue)
    {
        return driver.getCurrentUrl().equals(expectedValue) ;
    }

    public HomeScreen loginSteps (String user ,String pass)
    {
        setUserName(user);
        setPassword(pass);
        clickOnLoginButton();
        return new HomeScreen(driver);
    }


}
