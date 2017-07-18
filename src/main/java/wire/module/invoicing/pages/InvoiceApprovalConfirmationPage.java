package wire.module.invoicing.pages;

import appway.form.FormFiller;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wire.pages.WireFormPage;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/22/2016.
 */
public class InvoiceApprovalConfirmationPage extends WirePortalPage implements WireFormPage {
    public InvoiceApprovalConfirmationPage(WebDriver webDriver) {
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

}
