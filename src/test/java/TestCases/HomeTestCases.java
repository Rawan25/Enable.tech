package TestCases;

import Pages.CartScreen;
import Pages.HomeScreen;
import Pages.LoginScreen;
import Utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.time.Duration;
import static DriverManager.DriverManager.*;

public class HomeTestCases {

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
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1, description = "Verify that the products are added successfully in the cart by label")
    public void addProductsToCartForLabelsTC()
    {
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                ,Utilities.getJsonData("validUserData","password"));


        String[] SauceLabsBikeLightDetails=
                new HomeScreen(getDriver()).clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                        .getSauceLabsBikeLightProductDetails();

        String[] SauceLabsBackpackProductDetails=
                new HomeScreen(getDriver()).clickOnAddToCartButtonForSauceLabsBackpackProduct().
                        getSauceLabsBackpackProductDetails();


        new HomeScreen(getDriver()).clickOnShoppingCarButton();

        softAssert.assertTrue(new CartScreen(getDriver()).verifySauceLabsBikeLightProductLabel
                (SauceLabsBikeLightDetails[0])); //label
        softAssert.assertTrue(new CartScreen(getDriver()).verifySauceLabsBackpackProductLabel
                (SauceLabsBackpackProductDetails[0])); //label
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify that the products are added successfully in the cart by price")
    public void addProductsToCartForPricesTC()
    {
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                ,Utilities.getJsonData("validUserData","password"));


        String[] SauceLabsBikeLightDetails=
                new HomeScreen(getDriver()).clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                        .getSauceLabsBikeLightProductDetails();

        String[] SauceLabsBackpackProductDetails=
                new HomeScreen(getDriver()).clickOnAddToCartButtonForSauceLabsBackpackProduct().
                        getSauceLabsBackpackProductDetails();


        new HomeScreen(getDriver()).clickOnShoppingCarButton();

        softAssert.assertTrue(new CartScreen(getDriver()).verifySauceLabsBikeLightProductPrice
                (SauceLabsBikeLightDetails[1]));
        softAssert.assertTrue(new CartScreen(getDriver()).verifySauceLabsBackpackProductPrice
                (SauceLabsBackpackProductDetails[1]));
        softAssert.assertAll();
    }

    @Test(priority = 3,description = "Verify that the products are removed successfully from the shopping cart")
    public void removeProductFromCartTC(){
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnRemoveSauceLabsBikeLightButton();

        Assert.assertTrue(new CartScreen(getDriver()).verifyProductIsRemoved());


    }

    @Test(priority = 4,description = "Verify that Continue Shopping button navigates to Home screen successfully")
    public void navigateToContinueShoppingTC() throws IOException {
        new LoginScreen(getDriver()).loginSteps(Utilities.getJsonData("validUserData","username")
                        ,Utilities.getJsonData("validUserData","password"))
                .clickOnAddToCartButtonForSauceLabsBikeLightProduct()
                .clickOnAddToCartButtonForSauceLabsBackpackProduct()
                .clickOnShoppingCarButton().clickOnContinueShoppingButton();

        Assert.assertTrue(new LoginScreen(getDriver()).assertHomeScreen(Utilities.getPropertyValue("HomePageUrl")));

    }


    @AfterMethod
    public void quit()
    {
        quitDriver();
    }
}
