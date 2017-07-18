package wire.module.invoicing.pages;

import appway.form.FormFiller;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.TimingUtility;
import wire.module.invoicing.processes.InvoiceCreate;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/22/2016.
 */
public class InvoiceInputChargeDetailsPage extends WirePortalPage {


    public InvoiceInputChargeDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0,-4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

    public void fillForm(String formDirectoryIdentifier, InvoiceCreate.Form form) {
        String filename = formDirectoryIdentifier + "/" + form.getName();
        FormFiller formFiller = new FormFiller(webDriver);
        clickAddPosition();
        TimingUtility.waitMillis(250);
        clickAddPosition();
        // TODO Count the number of positions in the form file
        formFiller.fill(filename);
    }

    private void clickAddPosition() {
        WebElement buttonAddPosition = webDriver.findElement(By.id("invoiceFreetext_addPosition"));
        buttonAddPosition.click();
    }
}
