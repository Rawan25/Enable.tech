package TestCases;

import Pages.CartScreen;
import Pages.CheckoutScreen;
import Pages.LoginScreen;
import Utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverManager.DriverManager.*;

public class CartTestCases {
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

    @Test(priority = 1, description = "Verify that Continue button navigates to Checkout screen")
    public void validCheckoutInformation() throws IOException {
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("validUserData","first_name"))
                .setLastName(Utilities.getJsonData("validUserData","last_name"))
                .setPostalCode(Utilities.getJsonData("validUserData","zip_code"))
                .clickOnContinueButton();

        Assert.assertTrue(new CheckoutScreen(getDriver()).assertCheckoutScreen(
                Utilities.getPropertyValue("CheckoutPageURL")));

    }

    @Test(priority = 2,description = "Verify that error message appears when clicking on Continue button without " +
            "fill the inputs")
    public void emptyCheckoutInformation(){
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("inValidUserData","empty_first_name"))
                .setLastName(Utilities.getJsonData("inValidUserData","empty_last_name"))
                .setPostalCode(Utilities.getJsonData("inValidUserData","empty_zip_code"))
                .clickOnContinueButton();

        Assert.assertTrue(new CartScreen(getDriver()).isErrorMassageVisible());

    }

    @Test(priority = 3,description = "Verify that Cancel button returns to Cart screen")
    public void returnToHomeScreen() throws IOException {

        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("validUserData","first_name"))
                .setLastName(Utilities.getJsonData("validUserData","last_name"))
                .setPostalCode(Utilities.getJsonData("validUserData","zip_code"))
                .clickOnCancel();

        Assert.assertTrue(new CheckoutScreen(getDriver()).assertCheckoutScreen(
                Utilities.getPropertyValue("CartPageURL")));

    }

    @AfterMethod
    public void quit()
    {
        quitDriver();
    }
}
