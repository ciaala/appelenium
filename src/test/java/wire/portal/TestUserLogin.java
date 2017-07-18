package wire.portal;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import wire.login.LoginPage;
import wire.template.WirePortalPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by crypt on 5/19/2016.
 */
// Its a record and playback linear framework
public class TestUserLogin {
    static WebDriver webDriver;

    private LoginPage loginPage;
    private WirePortalPage portalPage;

    @BeforeClass
    public static void beforeClass() {
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://wire-dev.appway.com/");
    }

    @AfterClass
    public static void afterClass() {
        webDriver.quit();
        webDriver = null;
    }

    @Before
    public void before() {
        this.loginPage = new LoginPage(webDriver);
    }
    @Test
    public void userCanLoginWithValidDetails() {
        //
        loginPage.loginAttempt("USER", "PASSWORD");
        Assert.assertNotNull("Wire's header is missing", webDriver.findElement(By.className("awHeaderLogo")));
    }

    @Test
    public void userCannotLoginWithInvalidDetails() {
        //
        loginPage.loginAttempt("USER", "PASSWORD");
        Assert.assertNotNull("Wire's header is missing", webDriver.findElement(By.className("LoginForm")));
    }

    @Test
    public void userCanLoginAndLogout() {
        //
        loginPage.loginAttempt("fiduccia", "PASSWORD");
        portalPage.recognize();
        Assert.assertNotNull("Wire's header is missing", webDriver.findElement(By.className("LoginForm")));
    }




}
