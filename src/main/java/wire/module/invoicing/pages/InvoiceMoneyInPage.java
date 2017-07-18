package wire.module.invoicing.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.TimingUtility;
import wire.template.WirePortalPage;

/**
 * Created by crypt on 5/24/2016.
 */
public class InvoiceMoneyInPage extends WirePortalPage{

    public InvoiceMoneyInPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void recognize() {
        //super.recognize();
        //TODO enable the super recognize;
        String identifier = StringUtils.substring(getClass().getSimpleName(), 0,-4);
        Assert.assertTrue("Unable to recognize the page", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

    public void pressTodayButton() {
        WebElement todayButton = webDriver.findElements(By.className("Button")).stream().filter(
                e -> StringUtils.containsIgnoreCase(e.getText(), "Set to today")
        ).findFirst().orElse(null);
        Assert.assertNotNull("Unable to identify in the page the button 'Set to today'",todayButton);
        todayButton.click();

        TimingUtility.waitMillis();
    }

    public void pressRightAmountButton() {
        WebElement fullAmountButton = webDriver.findElements(By.className("Button")).stream().filter(
                e -> StringUtils.containsIgnoreCase(e.getText(), "Full amount paid")
        ).findFirst().orElse(null);
        Assert.assertNotNull("Unable to identify in the page the button 'Full amount paid'",fullAmountButton);
        fullAmountButton.click();
        TimingUtility.waitMillis();

    }
    public void pressCompleteButton() {
        WebElement completeButton = webDriver.findElements(By.className("Button")).stream().filter(
                e -> StringUtils.containsIgnoreCase(e.getText(), "Full amount paid")
        ).findFirst().orElse(null);
        Assert.assertNotNull("Unable to identify in the page the button 'Complete'",completeButton);
        completeButton.click();
        TimingUtility.waitMillis();
    }

}
