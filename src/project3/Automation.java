package project3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Automation {

    WebDriver driver;

    @Before
    public void setup() {

        String baseUrl = " http://automationpractice.com/index.php";

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void verifyErrorMessageWhenInvalidPasswordEntered() {
        WebElement signinlink = driver.findElement(By.linkText("Sign in"));
        signinlink.click();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("abc1@gmail.com");
        WebElement passwordField = driver.findElement(By.name("passwd"));
        passwordField.sendKeys("ab12");
        WebElement signinBtn = driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
        signinBtn.click();

        String expectedErrorMessage = "There is 1 error";
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()='There is 1 error']"));
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals("Error message not dispalyed", expectedErrorMessage, actualErrorMessage);
    }

    @After
    public void teardown() {

        driver.quit();
    }

}



