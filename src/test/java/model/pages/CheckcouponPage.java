package model.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class CheckcouponPage {
    private WebDriver driver;


    public CheckcouponPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By goToMobile = By.linkText("MOBILE");
    private final By SelectIphone = By.xpath("//a[contains(text(),'IPhone')]/following::button[@title='Add to Cart'][1]");
    private final By grandtotal = By.xpath("//*[@id='shopping-cart-totals-table']/tfoot");
    private final By Inputcoupon = By.xpath("//*[@id='coupon_code']");
    private final By ApplyDiscount = By.xpath("//*[@id='discount-coupon-form']/div/div/div/div/button");
    
  
    public void Input(String coupon) {
        driver.findElement(Inputcoupon).clear();;
        driver.findElement(Inputcoupon).sendKeys(coupon);
        driver.findElement(ApplyDiscount).click();
    }
    public void Mobile() {
        driver.findElement(goToMobile).click();
    }

    public void Select() {
        driver.findElement(SelectIphone).click();
    }
    public String getTotal() {
        WebElement grandTotal = driver.findElement(grandtotal);
        String origin = grandTotal.getText();
        System.out.println("origin: " + origin);
        return origin;
    }

    public String getTotal2() {
        WebElement newgrandTotal = driver.findElement(grandtotal);
        String discount = newgrandTotal.getText();
        System.out.println("discount: " + discount);
        return discount;
    }
    public void compare(String totalBeforeDiscount, String totalAfterDiscount) {
        Assert.assertNotEquals(totalBeforeDiscount, totalAfterDiscount, "grand total cannot be the same");
    }

}
