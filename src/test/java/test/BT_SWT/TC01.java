package test.BT_SWT;
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
import org.openqa.selenium.support.ui.Select;

public class TC01 {
    @Test
    public void main() throws InterruptedException {
        

        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

         //Step 1: get Link
        driver.get("http://live.techpanda.org/");

        // Step 2: Verify Title of the page
        String expectedTitle = "TechPanda";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verification passed");
        } else {
            System.out.println("Title verification failed");
        }

        Thread.sleep(2000);
        // Step 3: Click on MOBILE menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        Thread.sleep(2000);
        // Step 4: Select SORT BY dropdown as name
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select[title='Sort By']")));
        sortDropdown.selectByVisibleText("Name");
        
        Thread.sleep(2000);
        // Step 5: Verify all products are sorted by name
        java.util.List<WebElement> productNames = driver.findElements(By.cssSelector("h2.product-name a"));
        boolean isSorted = true;
        for (int i = 1; i < productNames.size(); i++) {
            if (productNames.get(i - 1).getText().compareTo(productNames.get(i).getText()) > 0) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            System.out.println("Products are sorted by name");
        } else {
            System.out.println("Products are not sorted by name");
        }

        // Take screen shot of the result
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "D:/study FPT/Fall2023/SWT301/Assignment/Selenium/selenium-webdriver-java-master/src/test/java/images/TC01.png";

        try {
            // Copy the screenshot file to the desired location
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
        // Close the browser
        driver.quit();
    }
}

