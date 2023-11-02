package test;

import driver.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC06_Test {
    @Test
    public void main() throws InterruptedException {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        TC06_LoginPage loginPage = new TC06_LoginPage(driver);
        TC06_CartPage cartPage = new TC06_CartPage(driver);
        TC06_CheckOutPage checkoutPage = new TC06_CheckOutPage(driver);
        //Step 1  Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        Thread.sleep(2000);
        //Step 2  Click on my account link
        loginPage.clickOnMyAccountLink();
        Thread.sleep(2000);
        //Step 3 Login in application using previously created credential
        loginPage.login("huuduc011@example.com", "123456");
        Thread.sleep(2000);
        //Step 4 Click on MY WISHLIST link
        cartPage.clickOnMyWishlistLink();
        Thread.sleep(2000);

        //Step 5 In next page, Click ADD TO CART link
        cartPage.clickOnMyAddToCartLink();
        Thread.sleep(2000);

        //Step 6 Enter general shipping country, state/province and zip for the shipping cost estimate
        cartPage.enterShippingInformation("United States", "New York", "123456");
        Thread.sleep(2000);

        //Step 7 Click Estimate
        cartPage.clickOnEstimateLink();
        Thread.sleep(2000);

        //Step 8 Verify Shipping cost generated
        String shippingCost = cartPage.getShippingCost();
        System.out.println("Shipping cost: " + shippingCost);
        
        //Step 9 Select Shipping Cost, Update Total
        cartPage.selectShippingCost();
        cartPage.updateTotalCost();
        Thread.sleep(2000);
        //Step 10 Verify shipping cost is added to total
        String totalCost = cartPage.getTotalCost();
        System.out.println("Total cost: " + totalCost);
        Thread.sleep(2000);

        //Step 11 Click "Proceed to Checkout"            
        checkoutPage.clickProceedToCheckout();
        Thread.sleep(2000);

        //Step 12a Enter Billing Information, and click Continue
        
        checkoutPage.enterBillingInformation( "Huu",
                 "Duc", "FPT", "24",
                "LVV", "THUDUC", "New York", "123456",
                "United States", "1234567890", "654321");
       
         
        checkoutPage.selectdiffent();
        Thread.sleep(2000);
        //Step 12b Enter Shipping Information, and click Continue
        checkoutPage.enterShippingInformation( "Huu",
                 "Duc", "Sango", "addd",
                "Nani", "San Diego", "New York", "123457",
                "United States", "999231890", "650901");
        Thread.sleep(2000);
        //Step 13 Enter Shipping Information, and click Continue
        checkoutPage.selectShippingMethod();
        Thread.sleep(2000);
        //Step 14  In Payment Information select 'Check/Money Order' radio button. Click Continue
        checkoutPage.selectPaymentMethod();
        Thread.sleep(2000);
        
        checkoutPage.clickPaymentInfo();
        Thread.sleep(2000);
        //Step 15 Click 'PLACE ORDER' button
        checkoutPage.clickPlaceOrder();

        String expectedMessage = "THANK YOU FOR YOUR PURCHASE!";
        String actualMessage = checkoutPage.getOrderRecievedMessage();
        assert actualMessage.equals(expectedMessage) : "Order failed!";
        System.out.println("Order sent succeed");
        Thread.sleep(2000);
        //Step 16 Verify Oder is generated. Note the order number
        String orderNumber = checkoutPage.getOrderNumber();
        System.out.println(orderNumber);
        Thread.sleep(2000);
        driver.quit();
    }
}