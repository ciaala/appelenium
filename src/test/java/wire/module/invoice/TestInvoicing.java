package wire.module.invoice;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverFactory;
import wire.User;
import wire.login.LoginInteraction;
import wire.module.invoicing.processes.Invoicing;

/**
 * Created by crypt on 5/21/2016.
 */
@FixMethodOrder(MethodSorters.JVM)
public class TestInvoicing {

    //private WebDriver webDriver;
    //private Invoicing invoicing;

    @BeforeClass
    public static void beforeClass() {


    }

    @AfterClass
    public static void afterClass() {
        // webDriver.quit();
        // webDriver = null;
    }

    @Before
    public void before() {

        
    }

    @After
    public void after() {

    }
    @Test
    public void startProcess() {
        WebDriver webDriver = WebDriverFactory.create();
        Invoicing invoicing = new Invoicing(webDriver);
        LoginInteraction.execute(webDriver, User.BACKOFFICE);

        invoicing.startProcess();
    }

    @Test
    public void scenarioCreateManualProcess() {
        WebDriver webDriver = WebDriverFactory.create();
        Invoicing invoicing = new Invoicing(webDriver);
        LoginInteraction.execute(webDriver, User.BACKOFFICE);

        invoicing.createProcess("invoicing/manualScenario/invoiceCreate");



    }
}
