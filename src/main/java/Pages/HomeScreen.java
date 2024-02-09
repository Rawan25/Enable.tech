package Pages;

import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeScreen {
    private WebDriver driver;
    private final By shoppingCardButton = By.className("shopping_cart_badge");
    private final By addToCartButtonForSauceLabsBackpackProduct = By.id("add-to-cart-sauce-labs-backpack");
    private final By sauceLabsBackpackProductLabel= By.id("item_4_title_link");
    private final By sauceLabsBackpackProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");

    private final By addToCartButtonForSauceLabsBikeLightProduct = By.id("add-to-cart-sauce-labs-bike-light");
    private final By sauceLabsBikeLightLabel= By.id("item_0_title_link");
    private final By sauceLabsBikeLightProductPrice = By.xpath("(//div[@class='inventory_item_price'])[2]");

    public HomeScreen(WebDriver driver) {
        this.driver = driver;
    }


    public HomeScreen clickOnAddToCartButtonForSauceLabsBackpackProduct () {
        Utilities.clickOnButton(driver, addToCartButtonForSauceLabsBackpackProduct);
        return this;
    }

    public String getSauceLabsBackpackProductLabel()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductLabel);
    }
    public String getSauceLabsBackpackProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductPrice);
    }
    public String[] getSauceLabsBackpackProductDetails()
    {
        return new String[]{getSauceLabsBackpackProductLabel(),getSauceLabsBackpackProductPrice()};
    }

    public HomeScreen clickOnAddToCartButtonForSauceLabsBikeLightProduct() {
        Utilities.clickOnButton(driver, addToCartButtonForSauceLabsBikeLightProduct);
        return this;
    }
    public String getSauceLabsBikeLightProductLabel()
    {
        return Utilities.getText(driver,sauceLabsBikeLightLabel);
    }
    public String getSauceLabsBikeLightProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBikeLightProductPrice);
    }
    public String[] getSauceLabsBikeLightProductDetails()
    {
        return new String[]{getSauceLabsBikeLightProductLabel(),getSauceLabsBikeLightProductPrice()};
    }

    public CartScreen clickOnShoppingCarButton() {
        Utilities.clickOnButton(driver, shoppingCardButton);
        return new CartScreen(driver);
    }

    public Boolean assertHomeScreen(String expectedValue)
    {
        return driver.getCurrentUrl().equals(expectedValue) ;
    }


}
