package wire.module.invoicing.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/21/2016.
 */
public class InvoiceInputMaskPage extends WirePortalPage {

    public InvoiceInputMaskPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0, -4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

}
