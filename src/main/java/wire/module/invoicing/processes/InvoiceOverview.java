package wire.module.invoicing.processes;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wire.template.WirePortalPage;
import wire.template.WireProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crypt on 5/21/2016.
 */
public class InvoiceOverview extends WirePortalPage implements WireProcess {

    public static final String DATATABLE_HEADER_PANEL_LABEL = "aw_widgets_datatable_HeaderPanel_label";

    public InvoiceOverview(WebDriver driver) {
        super(driver);
    }

    public void startProcess() {
        this.navigateTo("Finance", "Invoice Overview (New)");

    }

    @Override
    public void pressProcessButton(String name) {

    }

    public void pressCreateInvoice() {
        WebElement center = webDriver.findElement(By.className("aw_BorderLayoutManager_center"));
        Assert.assertNotNull("Unable to find the center content", center);
        WebElement button = center.findElements(By.className("Button")).stream()
                .filter(e -> "Create Invoice".equalsIgnoreCase(e.getText())
                ).findFirst().orElse(null);
        Assert.assertNotNull("Unable to find the button", button);
        button.click();
    }

    @Override
    public void recognize() {
        super.recognize();
        List<String> texts = new ArrayList<>();
        //Arrays.asList("Open Invoices", "Upcoming Schedules");
        webDriver.findElements(By.tagName("h3")).forEach(e -> {
            texts.remove(e.getText());
        });
        Assert.assertArrayEquals("Unable to recognize the web page with the overview of open invoices and invoice's recurrences",
                new String[]{"Open Invoices", "Upcoming Schedules"}, texts.toArray());
    }

    public void openTask(String invoiceNumber) {
        invoiceNumber = StringUtils.remove(invoiceNumber, "#");
        startProcess();
        WebElement dataTableComponent = webDriver.findElements(By.className("DataTableComponent")).stream().filter(
                e -> e.findElement(By.className(DATATABLE_HEADER_PANEL_LABEL))
                        .getText().equalsIgnoreCase("Invoice #")
        ).findFirst().orElse(null);
        Assert.assertNotNull(dataTableComponent);
        WebElement datatableContentPanel = dataTableComponent.findElement(By.className("aw_widgets_datatable_ContentPanel"));
        WebElement row = datatableContentPanel.findElement(By.xpath("//tr[./*/*/text()='" + invoiceNumber + "']"));
        WebElement magnifier = row.findElements(By.tagName("a")).stream().
                filter(e -> e.getAttribute("style").contains("magnifier")).findFirst().orElse(null);
        Assert.assertNotNull("The icon to open the process is missing: ", magnifier);
        magnifier.click();


    }
}
