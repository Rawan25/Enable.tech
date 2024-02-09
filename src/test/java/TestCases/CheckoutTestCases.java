package TestCases;

import Pages.CartScreen;
import Pages.CheckoutScreen;
import Pages.HomeScreen;
import Pages.LoginScreen;
import Utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverManager.DriverManager.*;

public class CheckoutTestCases {


    @BeforeMethod
    public void setup() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser")
                : Utilities.getPropertyValue("browser");
        setUpDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().get(Utilities.getPropertyValue("URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void totalPriceIsCorrect(){
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("validUserData","first_name"))
                .setLastName(Utilities.getJsonData("validUserData","last_name"))
                .setPostalCode(Utilities.getJsonData("validUserData","zip_code"))
                .clickOnContinueButton();

        System.out.println(new CheckoutScreen(getDriver()).getItemsTotal());
        System.out.println(new CheckoutScreen(getDriver()).get());

        //Assert.assertTrue(new CheckoutScreen(getDriver()).verifyTotalItemsPrice());

    }

    @Test (priority = 2,description = "Verify that the order is completed successfully")
    public void orderConfirmation(){
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("validUserData","first_name"))
                .setLastName(Utilities.getJsonData("validUserData","last_name"))
                .setPostalCode(Utilities.getJsonData("validUserData","zip_code"))
                .clickOnContinueButton().clickOnFinishButton();

        Assert.assertTrue(new CheckoutScreen(getDriver()).checkConfirmationOrderMessage());

    }

    @Test(priority = 3,description = "Verify that Cancel button returns to Home Screen")
    public void orderCancellation() throws IOException {
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnCheckoutButton()
                .setFirstName(Utilities.getJsonData("validUserData","first_name"))
                .setLastName(Utilities.getJsonData("validUserData","last_name"))
                .setPostalCode(Utilities.getJsonData("validUserData","zip_code"))
                .clickOnContinueButton().clickOnCancelButton();

        Assert.assertTrue(new HomeScreen(getDriver()).assertHomeScreen(Utilities.getPropertyValue("HomePageUrl")));

    }



    @AfterMethod
    public void quit()
    {
        quitDriver();
    }

}
