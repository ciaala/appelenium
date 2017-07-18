package wire.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wire.User;

/**
 * Created by crypt on 5/21/2016.
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://wire-dev.appway.com");
    }

    public void loginAttempt(User user) {
        loginAttempt(user.getUsername(), user.getPassword());
    }

    public void loginAttempt(String username, String password) {
        this.driver.findElement(By.id("username")).sendKeys(username);
        this.driver.findElement(By.id("password")).sendKeys(password);
        this.driver.findElement(By.className("Button")).click();
    }


}
