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





}
