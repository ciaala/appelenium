package wire.module.invoicing.pages;

import appway.form.FormFiller;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/24/2016.
 */
public class InvoiceIssueWaitPage extends WirePortalPage {


    public InvoiceIssueWaitPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0,-4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }
}
