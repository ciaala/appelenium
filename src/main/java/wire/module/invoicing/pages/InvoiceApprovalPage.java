package wire.module.invoicing.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/24/2016.
 */
public class InvoiceApprovalPage extends WirePortalPage {


    public InvoiceApprovalPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getApprovalComment() {
        return webDriver.findElement(By.id("invoiceApprovalComment")).getText();
    }

    public void setApprovalComment(String text) {
        WebElement invoiceApprovalComment = webDriver.findElement(By.id("invoiceApprovalComment"));
        invoiceApprovalComment.clear();
        invoiceApprovalComment.sendKeys(text);

    }

    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0,-4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

 }
