package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev-dash.janitri.in/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testBlankLogin() {
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Invalid Credentials") || error.contains("required"),
            "Expected error message not found. Actual: " + error);
    }

    @Test
    public void testInvalidLogin() {
        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error.trim(), "Invalid Credentials");
    }

    @Test
    public void testPasswordToggle() throws InterruptedException {
        loginPage.enterPassword("dummyPass");
        loginPage.togglePasswordVisibility();
        Thread.sleep(1000); // brief pause to see the effect
        loginPage.togglePasswordVisibility();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}