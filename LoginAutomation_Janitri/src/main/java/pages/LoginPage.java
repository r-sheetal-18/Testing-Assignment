package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By usernameField = By.id("formEmail");
    By passwordField = By.id("formPassword");
    By loginButton = By.cssSelector(".login-button");
    By eyeIcon = By.cssSelector(".passowrd-visible");
    By errorMessage = By.cssSelector(".invalid-credential-div .normal-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void togglePasswordVisibility() {
        driver.findElement(eyeIcon).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}