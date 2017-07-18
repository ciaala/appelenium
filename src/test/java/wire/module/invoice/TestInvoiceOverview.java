package wire.module.invoice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverFactory;
import wire.User;
import wire.login.LoginInteraction;
import wire.module.invoicing.processes.InvoiceOverview;

/**
 * Created by crypt on 5/22/2016.
 */
public class TestInvoiceOverview {
    private WebDriver webDriver;
    private InvoiceOverview invoiceOverview;


    @After
    public void after() {
        webDriver = null;
    }

    @Before
    public void before() {
        webDriver = WebDriverFactory.create();
        invoiceOverview = new InvoiceOverview(webDriver);
    }


    @Test
    public void startProcess() {
        LoginInteraction.execute(webDriver, User.BACKOFFICE);

        invoiceOverview.startProcess();
    }

    @Test
    public void scenarioOpenInvoice() {

        invoiceOverview.startProcess();
        String invoiceNumber = "201605136";

        invoiceOverview.openTask(invoiceNumber);

    }
}
