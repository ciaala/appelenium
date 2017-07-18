package wire.module.invoicing.pages;

import appway.form.FormFiller;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wire.module.invoicing.processes.InvoiceCreate;
import wire.pages.WireFormPage;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/22/2016.
 */
public class InvoiceCompletePage extends WirePortalPage implements WireFormPage {
    private static final Logger logger = LogManager.getLogger(InvoiceCreate.class);

    public InvoiceCompletePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void fillForm(String formDirectoryIdentifier) {
        FormFiller formFiller = new FormFiller(webDriver);
        formFiller.fill(formDirectoryIdentifier + "/form" + getClass().getSimpleName() + ".properties");

    }


    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0,-4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

    public void pressForwardApprobalButton(String approvalButtonText) {
        WebElement element = webDriver.findElement(By.id("approvalButton"));
        Assert.assertNotNull("Unable to find the approval button", element);
        Assert.assertTrue(StringUtils.join("Button 'Forward for approval has unexpected text : '" + element.getText() + "'"), approvalButtonText.equals(element.getText()));
        element.click();
    }

    public String getInvoiceNumber() {
        recognize();
        WebElement compactInvoiceInfo = webDriver.findElement(By.id("compactInvoiceInfo"));
        WebElement invoiceInfobox = compactInvoiceInfo.findElements(By.className("Infobox")).stream().
                filter(
                        e -> e.findElement(By.className("InfoboxLabel")).getText().equalsIgnoreCase("Invoice")
                ).findFirst().orElse(null);
        Assert.assertNotNull("Cannot find the invoice infobox in the compact header", invoiceInfobox);

        String invoiceNumber = invoiceInfobox.findElement(By.className("Label")).getText();

        logger.debug(StringUtils.join("Identified the InvoiceNumber: ", invoiceNumber));
        return invoiceNumber;
    }
}
