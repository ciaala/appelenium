package wire.module.invoicing.processes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.TimingUtility;
import selenium.WebDriverFactory;
import wire.User;
import wire.login.LoginInteraction;
import wire.login.LoginPage;
import wire.module.invoicing.pages.InvoiceApprovalConfirmationPage;
import wire.module.invoicing.pages.InvoiceApprovalPage;
import wire.module.invoicing.pages.InvoiceIssueWaitPage;
import wire.module.invoicing.pages.InvoiceMoneyInPage;
import wire.template.WirePortalPage;
import wire.template.WireProcess;

/**
 * Created by crypt on 5/21/2016.
 */
public class Invoicing extends WirePortalPage implements WireProcess {


    private LoginPage loginPage;

    public Invoicing(WebDriver webDriver) {
        super(webDriver);


    }

    public void startProcess() {
        InvoiceOverview invoiceOverview = new InvoiceOverview(webDriver);
        invoiceOverview.startProcess();

    }

    @Override
    public void pressProcessButton(String name) {
        if (webDriver.findElements(By.className("aw_BorderLayoutManager_south")).size() > 0) {
            WebElement south = webDriver.findElement(By.className("aw_BorderLayoutManager_south"));
            south.findElements(By.cssSelector(".FlowBarElement .Button")).stream().filter(e ->
                    name.equals(e.getText())
            ).findFirst().orElse(null);
        }
    }

    public void createProcess(String formDirectoryIdentifier) {

        String invoiceNumber = backOfficeCreateInvoice(formDirectoryIdentifier);

        reviewerApproveInvoice(invoiceNumber, formDirectoryIdentifier);

        issuerIssueAndPaymentInvoice(invoiceNumber, formDirectoryIdentifier);
    }

    private void issuerIssueAndPaymentInvoice(String invoiceNumber, String formDirectoryIdentifier) {
        WebDriver webDriver3 = WebDriverFactory.create();
        new LoginInteraction().execute(webDriver3, User.FINANCE);

/*        InvoiceOverview invoiceOverview = new InvoiceOverview(webDriver3);
        invoiceOverview.openTask(invoiceNumber);
*/


        WirePortalPage portalPage = new WirePortalPage(webDriver3);
        portalPage.recognize();

        portalPage.openGroupTask(invoiceNumber);

        InvoiceApproval invoiceApproval = new InvoiceApproval(webDriver3);
        InvoiceApprovalPage invoiceApprovalPage = invoiceApproval.getInvoiceApprovalPage();
        invoiceApprovalPage.setApprovalComment("Nothing wrong");

        invoiceApproval.pressProcessButton("Issue");

        InvoiceIssueWaitPage issueWaitPage = getInvoiceIssueWaitPage(webDriver3);
        issueWaitPage.recognize();

        pressProcessButton("Payment");

        InvoiceMoneyInPage moneyInPage = new InvoiceMoneyInPage(webDriver3);
        moneyInPage.recognize();


        moneyInPage.pressTodayButton();

        moneyInPage.pressRightAmountButton();

        moneyInPage.pressCompleteButton();

        //portalPage = new WirePortalPage(webDriver3);
        portalPage.recognize();
    }

    private InvoiceIssueWaitPage getInvoiceIssueWaitPage(WebDriver webDriver) {
        return new InvoiceIssueWaitPage(webDriver);
    }

    private void reviewerApproveInvoice(String invoiceNumber, String formDirectoryIdentifier) {
        WebDriver webDriver2 = WebDriverFactory.create();
        new LoginInteraction().execute(webDriver2, User.ORDER_MANAGER);
        InvoiceOverview invoiceOverview = new InvoiceOverview(webDriver2);
        invoiceOverview.openTask(invoiceNumber);

        InvoiceApproval invoiceApproval = new InvoiceApproval(webDriver2);
        InvoiceApprovalPage invoiceApprovalPage = invoiceApproval.getInvoiceApprovalPage();
        invoiceApprovalPage.setApprovalComment("Nothing wrong");

        invoiceApproval.pressProcessButton("Approve");

        invoiceApproval.getPageApprovalConfirmation().recognize();
        this.pressProcessButton("Close");

    }

    private String backOfficeCreateInvoice(String formDirectoryIdentifier) {

        InvoiceOverview invoiceOverview = new InvoiceOverview(webDriver);

        invoiceOverview.startProcess();
        invoiceOverview.pressCreateInvoice();

        InvoiceCreate invoiceCreate = new InvoiceCreate(webDriver);
        String invoiceNumber = invoiceCreate.create(formDirectoryIdentifier);


        InvoiceApproval invoiceApproval = new InvoiceApproval(webDriver);
        InvoiceApprovalConfirmationPage approvalConfirmation = invoiceApproval.getPageApprovalConfirmation();

        approvalConfirmation.recognize();
        invoiceApproval.pressProcessButton("Close");

        WirePortalPage portalPage = new WirePortalPage(webDriver);
        portalPage.recognize();

        return invoiceNumber;
    }

}
