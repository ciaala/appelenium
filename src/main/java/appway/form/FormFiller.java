package appway.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by crypt on 5/21/2016.
 */
public class FormFiller {

    private static final Logger logger = LogManager.getLogger(FormFiller.class);
    private final WebDriver webDriver;


    public void fillDropdownList(HashMap<String, FormElement> elements, FormElement fe, WebElement element) {
        String elementClass = element.getAttribute("class");
        if (StringUtils.contains(elementClass, "select2-container")) {
            element.click();
            elements.remove(fe.name);
            String key = fe.name.substring(5);
            FormElement tmp = elements.get(key);
            element.sendKeys(tmp.value);
            element.sendKeys(Keys.RETURN);
            elements.remove(tmp.name);
            doWaitBlockUI();
        } else {
            element.click();
            element.sendKeys(fe.value);
            element.sendKeys(Keys.RETURN);
        }

    }

    public void fill(String filename) {
        long startTime = System.currentTimeMillis();
        Form form = FormReader.read(filename);
        HashMap<String, FormElement> tmpElements = form.elements;
        int formInputModified;

        do {
            Collection<FormElement> values = new ArrayList<>(tmpElements.size());
            values.addAll(tmpElements.values());

            values.forEach(
                    (formElement) -> {
                        try {
                            List<WebElement> elements = webDriver.findElements(By.id(formElement.name));
                            if (elements.size() == 1) {
                                WebElement element = elements.get(0);
                                if (element.isDisplayed() && element.isEnabled()) {
                                    logger.debug("Identified: " + formElement.name);
                                    switch (formElement.type) {
                                        case DROPDOWNLIST: {
                                            this.fillDropdownList(tmpElements, formElement, element);
                                            break;
                                        }
                                        case CHECKBOXLISTCHECKBOX: {
                                            this.fillCheckboxList(tmpElements, formElement, element);
                                            break;
                                        }
                                        case TEXTAREA:
                                        case TEXTFIELD:
                                        case DATEPICKER:
                                            element.clear();
                                        default: {
                                            element.sendKeys(formElement.value);
                                            tmpElements.remove(formElement.name);
                                        }
                                    }
                                } else {

                                }
                            }
                        } catch (WebDriverException e) {
                            logger.error(StringUtils.join("Unable to set the form field ", formElement.name), e);
                        }
                    }
            );
            formInputModified = values.size() - tmpElements.size();
            logger.debug(StringUtils.join("Form element modified: ", formInputModified));
        } while (formInputModified > 0);
        doWaitBlockUI();
        long stopTime = System.currentTimeMillis();
        logger.debug(StringUtils.join("Form filled in: ", stopTime - startTime, "ms"));
        if (tmpElements.size() > 0) {
            logger.debug(StringUtils.join("Form elements filled: ", form.elements.size() - tmpElements.size()));
            logger.debug(StringUtils.join("Form elements not filled: ", tmpElements.size()));
        } else {
            logger.debug(StringUtils.join("All the forms have been filled: ", tmpElements.size()));
        }
    }

    private void fillCheckboxList(HashMap<String, FormElement> tmpElements, FormElement formElement, WebElement element) {
        boolean checked = Boolean.valueOf(element.getAttribute("checked"));
        if (formElement.checked != checked) {
            element.click();
        }
        tmpElements.remove(formElement.name);
    }

    private void doWaitBlockUI() {
        long startTime = System.currentTimeMillis();
        long yieldCounter = 0;
        while (webDriver.findElements(By.className("blockOverlay")).size() > 0) {
            Thread.yield();
            yieldCounter++;
        }
        long stopTime = System.currentTimeMillis();
        logger.debug(StringUtils.join("Waited: ", (stopTime - startTime), "ms ", "(yielded: ", yieldCounter, ")"));
    }

    public FormFiller(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
