package wire.login;

import org.openqa.selenium.WebDriver;
import wire.User;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/21/2016.
 */
public class LoginInteraction {

    public static void execute(WebDriver webDriver) {
        execute(webDriver, User.NUMCOM_USER);
    }
    public static void execute(WebDriver webDriver, User user) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginAttempt(user);
        WirePortalPage portalPage = new WirePortalPage(webDriver);
        portalPage.recognize();
    }
}
