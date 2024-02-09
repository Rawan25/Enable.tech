package Pages;

import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutScreen {

    private WebDriver driver;
    private final By itemTotalPrice = By.xpath("//div[@class='summary_info']/div[6]");
    ////*[@id="checkout_summary_container"]/div/div[2]/div[6]
    private final By finishButton = By.id("finish");
    private final By confirmationOrderMessage = By.xpath("//div[@id='checkout_complete_container']/h2");
    ////*[@id="checkout_complete_container"]/h2

    private final By cancelButton = By.id("cancel");

    private final By sauceLabsBikeLightProductPrice = By.xpath("(//div[@class='inventory_item_price'])[2]");
    private final By sauceLabsBackpackProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");

    public CheckoutScreen(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutScreen clickOnFinishButton() {
        Utilities.clickOnButton(driver, finishButton);
        return this;
    }

    public boolean checkConfirmationOrderMessage(){
        return Utilities.isElementVisible(driver,confirmationOrderMessage);
    }

    public Boolean assertCheckoutScreen(String expectedValue)
    {
        return driver.getCurrentUrl().equals(expectedValue) ;
    }

    public HomeScreen clickOnCancelButton(){
        Utilities.clickOnButton(driver,cancelButton);
        return new HomeScreen(driver);
    }
    public String getSauceLabsBikeLightProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBikeLightProductPrice);
    }

    public String getSauceLabsBackpackProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductPrice);
    }

    public double getItemsTotal() {
       return Utilities.getSum(getSauceLabsBikeLightProductPrice(), getSauceLabsBackpackProductPrice());
    }


    public boolean verifyTotalItemsPrice(){
        return Utilities.getText(driver,itemTotalPrice).equals(getItemsTotal());
    }

    public String get(){
       return Utilities.getText(driver,itemTotalPrice);
    }
}
