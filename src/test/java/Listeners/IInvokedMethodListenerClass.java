package Listeners;

import Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import java.io.IOException;

import static DriverManager.DriverManager.getDriver;


public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult testResult){

    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult testResult){
        if( testResult.getStatus() == ITestResult.FAILURE) {
            try {
                Utilities.takeScreenshot(getDriver(),testResult.getName());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


