package wire.template;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.TimingUtility;
import selenium.WebDriverFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by crypt on 5/20/2016.
 */
public class WirePortalPage extends WireBasePage {


    public WirePortalPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void recognize() {
        Assert.assertNotNull("Wire's header is missing", webDriver.findElement(By.className("awHeaderLogo")));
        String identifier = "StartScreen:wire_portal_v2.0";

        Assert.assertTrue("The current page is not a Wire Portal Page ", StringUtils.contains(webDriver.getPageSource(), identifier));
    }

    public void navigateTo(String parentMenuText, String menuText) {
        WebElement parentMenuLink = getMainMenuElement(parentMenuText);
        Assert.assertNotNull("Unable to find the parentMenuText '" + parentMenuText + "'", parentMenuLink);
        parentMenuLink.click();
        //parentMenuLink = getMainMenuElement(parentMenuText);
        //Assert.assertNotNull("Unable to find the parentMenuText '" + parentMenuText + "'", parentMenuLink);
        WebElement menuLink = webDriver.findElement(By.partialLinkText(menuText));
        Assert.assertNotNull("Unable to find the menu '" + menuText + "'", menuLink);
        menuLink.click();
    }

    private WebElement getMainMenuElement(String parentMenuText) {
        return this.webDriver.findElement(By.className("wire_mainnav")).findElements(By.className("Link")).stream()
                .filter(e ->
                        parentMenuText.equalsIgnoreCase(e.getText())
                ).findFirst().orElse(null);
    }

    private void logout() {
        List<WebElement> buttons = webDriver.findElements(By.className("Button"));
        List<WebElement> collect = buttons.stream().filter(e -> "Logout".equals(e.getText())).collect(Collectors.toList());
        if (collect != null && collect.size() == 1) {
            collect.get(0).click();
        }
    /*
        .forEach((e) -> {
            if (e != null && "Logout".equals(e.getText())) {
                e.click();
                return;
            }
        });
        */
    }


    public void openGroupTask(String invoiceNumber) {
        WebElement statusButton = webDriver.findElements(By.className("aw_com_nm_extensions_statusbuttons_ButtonText")).stream().filter(
                e -> StringUtils.containsIgnoreCase(e.getText(), "Tasks in my Role or Group")
        ).findFirst().orElse(null);
        Assert.assertNotNull("Unable to find the Group Task Button ", statusButton);

        statusButton.click();
        TimingUtility.waitMillis();

        List<WebElement> elements = webDriver.findElements(By.xpath("//*[starts-with(./text(),'" + invoiceNumber + "']"));

        // TODO Incomplete
        Assert.assertTrue("Invoice not found in the group task", elements != null && elements.size() > 0);
        WebElement task = elements.get(0);
        task.click();
        TimingUtility.waitMillis(1000);
    }


}
