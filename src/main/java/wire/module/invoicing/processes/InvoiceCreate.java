package wire.module.invoicing.processes;

import appway.form.FormFiller;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.TimingUtility;
import wire.module.invoicing.pages.InvoiceCompletePage;
import wire.module.invoicing.pages.InvoiceInputChargeDetailsPage;
import wire.module.invoicing.pages.InvoiceInputLetterPage;
import wire.module.invoicing.pages.InvoiceInputMaskPage;
import wire.pages.WireFormPage;
import wire.template.WirePortalPage;
import wire.template.WireProcess;

/**
 * Created by crypt on 5/21/2016.
 */
public class InvoiceCreate extends WirePortalPage implements WireProcess {
    private static final Logger logger = LogManager.getLogger(InvoiceCreate.class);

    public String create(String formDirectoryIdentifier) {
        long startTime = System.currentTimeMillis();
        //
        InvoiceInputMaskPage inputMaskPage = new InvoiceInputMaskPage(webDriver);
        inputMaskPage.recognize();
        fillForm(formDirectoryIdentifier, Form.INVOICE_INPUT_MASK);
        //
        pressProcessButton("Next");
        //
        InvoiceInputChargeDetailsPage chargeDetailsPage = new InvoiceInputChargeDetailsPage(webDriver);
        chargeDetailsPage.recognize();
        chargeDetailsPage.fillForm(formDirectoryIdentifier, Form.INVOICE_CHARGE_DETAILS);
        //
        pressProcessButton("Next");
        //
        WireFormPage formPage = new InvoiceInputLetterPage(webDriver);
        formPage.recognize();
        formPage.fillForm(formDirectoryIdentifier);
        //
        pressProcessButton("Next");
        //
        InvoiceCompletePage invoiceCompletePage = new InvoiceCompletePage(webDriver);
        invoiceCompletePage.recognize();
        invoiceCompletePage.fillForm(formDirectoryIdentifier);
        //
        String invoiceNumber = invoiceCompletePage.getInvoiceNumber();
        invoiceCompletePage.pressForwardApprobalButton("Send for Approval");

        long stopTime = System.currentTimeMillis();
        logger.debug(StringUtils.join("Create Process Execution Time: ", stopTime - startTime, "ms"));

        return invoiceNumber;
    }

    public enum Form {
        INVOICE_INPUT_MASK, INVOICE_CHARGE_DETAILS;

        public String getName() {
            String capitalized = WordUtils.capitalizeFully(StringUtils.replaceChars(this.name(), '_', ' '));
            String stripped = StringUtils.join("form", StringUtils.remove(capitalized, ' '), ".properties");
            return stripped;
        }
    }

    public InvoiceCreate(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillForm(String formDirectoryIdentifier, Form form) {
        String filename = formDirectoryIdentifier + "/" + form.getName();
        FormFiller formFiller = new FormFiller(webDriver);
        formFiller.fill(filename);
    }

    @Override
    public void startProcess() {

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
            TimingUtility.waitMillis();
            logger.debug(StringUtils.join("Process Action: ", name));
        }
    }
}
