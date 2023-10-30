package test;

import org.testng.annotations.Test;
import driver.driverFactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TC02 {
    @Test
    public void main() throws InterruptedException {

        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: get Link
        driver.get("http://live.techpanda.org/");
        
        Thread.sleep(2000);
        // Step bonus: Verify Title of the page
        String expectedTitle = "TechPanda";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Verification successfully");
        } else {
            System.out.println("Verification failed");
        }

        Thread.sleep(2000);
        // Step 2: Click on MOBILE menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        Thread.sleep(2000);
        // Step 3: Read the cost of Sony Xperia mobile
        String listPageCost = driver.findElement(By.xpath("//h2[contains(@class, 'product-name')]/a[text()='Sony Xperia']/parent::h2/following-sibling::div/span[@class='regular-price']/span[@class='price']")).getText();
        
        Thread.sleep(2000);
        // Step 4: Click on Sony Xperia mobile
        driver.findElement(By.linkText("SONY XPERIA")).click();
        
        Thread.sleep(2000);
         // Step 5: Read the Sony Xperia mobile cost from the detail page
         String detailPageCost = driver.findElement(By.cssSelector("span#product-price-1")).getText();
         
         Thread.sleep(2000);
         // Step 6: Compare the product cost in the list and details page
         assert listPageCost.equals(detailPageCost) : "Product cost mismatch";
         
         // Take screen shot of the result
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "D:/study FPT/Fall2023/SWT301/Assignment/Selenium/selenium-webdriver-java-master/src/test/java/images/TC02.png";

        try {
            // Copy the screenshot file to the desired location
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        driver.quit();
    }
}
