package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    // Declare element selector value here
    private By emailSelector = By.id("email_address");
    private By passwordSelector = By.id("password");
    private By loginBtnSelector = By.cssSelector("[type='submit']");

    // Constructor with required parameter as a WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Getter to return element on the page
    public WebElement email() {
        return driver.findElement(emailSelector);
    }

    public WebElement password() {
        return driver.findElement(passwordSelector);
    }

    public WebElement loginBtn() {
        return driver.findElement(loginBtnSelector);
    }

    public void inputEmail(String email){
        WebElement emilElement = driver.findElement(emailSelector);
        emilElement.sendKeys(email);
    }

    public void inputPassword(String password){
        password().sendKeys(password);
    }

    public void clickOnLoginBtn(){
        loginBtn().click();
    }
}
