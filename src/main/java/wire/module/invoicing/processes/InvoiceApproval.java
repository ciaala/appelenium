package wire.module.invoicing.processes;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wire.module.invoicing.pages.InvoiceApprovalConfirmationPage;
import wire.module.invoicing.pages.InvoiceApprovalPage;
import wire.template.WireProcess;

/**
 * Created by crypt on 5/21/2016.
 */
public class InvoiceApproval implements WireProcess {
    private static Logger logger = LogManager.getLogger(InvoiceCreate.class);

    private final WebDriver webDriver;
    private InvoiceApprovalPage invoiceApprovalPage;

    public InvoiceApproval(WebDriver webDriver) {
        super();
        this.webDriver = webDriver;
    }

    @Override
    public void startProcess() {
        //TODO Cannot be started
        //Extends a different Parent
    }

    @Override
    public void pressProcessButton(String name) {
        if (webDriver.findElements(By.className("aw_BorderLayoutManager_south")).size() > 0) {
            WebElement south = webDriver.findElement(By.className("aw_BorderLayoutManager_south"));
            WebElement webElement = south.findElements(By.cssSelector(".FlowBarElement .Button")).stream()
                    .filter(e ->
                            name.equalsIgnoreCase(e.getText())
                    ).findFirst().orElse(null);
            Assert.assertNotNull(StringUtils.join("Cannot find the process's button: '", name, "'"), webElement);
            webElement.click();
            Thread.yield();
            logger.debug(StringUtils.join("Process Action: ", name));
        }
    }

    public InvoiceApprovalConfirmationPage getPageApprovalConfirmation() {
        return new InvoiceApprovalConfirmationPage(webDriver);
    }

    public InvoiceApprovalPage getInvoiceApprovalPage() {
        return new InvoiceApprovalPage(webDriver);
    }
}
