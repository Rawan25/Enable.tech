package TestCases;

import Pages.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Utilities;

import java.io.IOException;
import java.time.Duration;

import static DriverManager.DriverManager.*;

public class LoginTestCases {
    @BeforeMethod
    public void setup() throws IOException {
        String browser = System.getProperty("browser")!= null ? System.getProperty("browser")
                : Utilities.getPropertyValue("browser");
        setUpDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().get(Utilities.getPropertyValue("URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }


    @Test(priority = 1,description = "Verify that the user can login with valid credentials")
    public void validLoginTC()  throws IOException {
        new LoginScreen(getDriver()).setUserName(Utilities.getJsonData("validUserData","username"))
                .setPassword(Utilities.getJsonData("validUserData","password"))
                .clickOnLoginButton();
        Assert.assertTrue(new LoginScreen(getDriver()).assertHomeScreen(Utilities.getPropertyValue("HomePageUrl")));
    }

    @Test(priority = 2,description = "Verify that the user can't login with invalid username")
    public void invalidLoginWithWrongUsernameTC()  throws IOException {
        new LoginScreen(getDriver()).setUserName(Utilities.getJsonData("inValidLoginData","wrong_username"))
                .setPassword(Utilities.getJsonData("inValidLoginData","correct_password"))
                .clickOnLoginButton();
        Assert.assertFalse(new LoginScreen(getDriver()).assertHomeScreen(Utilities.getPropertyValue("HomePageUrl")));
    }

    @Test(priority = 3,description = "Verify that the user can't login with invalid password")
    public void invalidLoginWithWrongPasswordTC()  throws IOException{
        new LoginScreen(getDriver()).setUserName(Utilities.getJsonData("inValidLoginData",
                        "correct_username"))
                .setPassword(Utilities.getJsonData("inValidLoginData","wrong_password"))
                .clickOnLoginButton();
        Assert.assertFalse(new LoginScreen(getDriver()).assertHomeScreen(Utilities.getPropertyValue("HomePageUrl")));
    }


    @AfterMethod
    public void quit()
    {
        quitDriver();
    }
}
