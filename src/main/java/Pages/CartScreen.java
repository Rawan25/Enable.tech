package Pages;

import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartScreen {
    private WebDriver driver;
    private final By sauceLabsBackpackProductLabel= By.id("item_4_title_link");

    private final By sauceLabsBackpackProductPrice = By.xpath("(//div[@class='inventory_item_price'])[2]");
    private final By sauceLabsBikeLightLabel= By.id("item_0_title_link");

    private final By sauceLabsBikeLightProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");

    private final By removeSauceLabsBikeLightButton = By.id("remove-sauce-labs-bike-light");

    private final By cartQuantity = By.className("cart_quantity");

    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    private final By cancelButton = By.id("cancel");

    private final By checkoutErrorMessage = By.className("error-message-container");

    public CartScreen(WebDriver driver) {
        this.driver = driver;
    }

    public String getSauceLabsBackpackProductLabel()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductLabel);
    }

    public String getSauceLabsBackpackProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductPrice);
    }
    public Boolean verifySauceLabsBackpackProductLabel(String label)
    {
        return getSauceLabsBackpackProductLabel().equals(label);
    }

    public Boolean verifySauceLabsBackpackProductPrice(String price)
    {
        return getSauceLabsBackpackProductPrice().equals(price);
    }

    public String getSauceLabsBikeLightLabel()
    {
        return Utilities.getText(driver,sauceLabsBikeLightLabel);
    }

    public String getSauceLabsBikeLightProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBikeLightProductPrice);
    }
    public Boolean verifySauceLabsBikeLightProductLabel(String label)
    {
        return getSauceLabsBikeLightLabel().equals(label);
    }

    public Boolean verifySauceLabsBikeLightProductPrice(String price)
    {
        return getSauceLabsBikeLightProductPrice().equals(price);
    }

    public CartScreen clickOnRemoveSauceLabsBikeLightButton(){
        Utilities.clickOnButton(driver, removeSauceLabsBikeLightButton);
        return this;
    }

    public String getCartItemsValue()
    {
        return Utilities.getText(driver,cartQuantity);
    }

    public String getShoppingCartBadgeNumber()
    {
        return Utilities.getText(driver,shoppingCartBadge);
    }

    public Boolean verifyProductIsRemoved()
    {
        return getCartItemsValue().equals(getShoppingCartBadgeNumber());
    }

    public HomeScreen clickOnContinueShoppingButton(){
        Utilities.clickOnButton(driver,continueShoppingButton);
        return new HomeScreen(driver);
    }


    public CartScreen clickOnCheckoutButton() {
        Utilities.clickOnButton(driver, checkoutButton);
        return this;
    }

    public CartScreen setFirstName(String data) {
        Utilities.setData(driver, firstNameInput, data);
        return this;
    }

    public CartScreen setLastName(String data) {
        Utilities.setData(driver, lastNameInput, data);
        return this;
    }

    public CartScreen setPostalCode(String data) {
        Utilities.setData(driver, postalCodeInput, data);
        return this;
    }

    public CheckoutScreen clickOnContinueButton() {
        Utilities.clickOnButton(driver, continueButton);
        return new CheckoutScreen(driver);
    }

    public boolean isErrorMassageVisible(){
        return Utilities.isElementVisible(driver,checkoutErrorMessage);
    }

    public CartScreen clickOnCancel(){
        Utilities.clickOnButton(driver,cancelButton);
        return this;
    }


}

