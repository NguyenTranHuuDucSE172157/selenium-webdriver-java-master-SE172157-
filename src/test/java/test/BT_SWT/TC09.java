package test.BT_SWT;

import java.time.Duration;
import driver.driverFactory;
import model.pages.CheckcouponPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TC09 {
    @Test
    public void main() throws InterruptedException{
        WebDriver driver = driverFactory.getChromeDriver();
        CheckcouponPage check = new CheckcouponPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    
         //Step 1: get Link
        driver.get("http://live.techpanda.org/");
         // Step 2: Click on MOBILE menu and add IPHONE to cart
        check.Mobile();
        check.Select();
        // Step 3:  Enter Coupon Code
        String totalBeforeDiscount = check.getTotal();
        check.Input("GURU50");
        Thread.sleep(2000);
        
        // Step 4:  Verify the discount generated
        String totalAfterDiscount = check.getTotal2();
        Thread.sleep(2000);
       
        check.compare(totalBeforeDiscount, totalAfterDiscount);
        Thread.sleep(2000);
        driver.quit();
    }
    

}
