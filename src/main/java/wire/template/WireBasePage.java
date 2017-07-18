package wire.template;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by crypt on 5/20/2016.
 */
public abstract class WireBasePage {

    protected WebDriver webDriver;

    WireBasePage(WebDriver driver) {
        this.webDriver = driver;
    }

    private static Logger logger = LogManager.getLogger(WireBasePage.class);

    public abstract void recognize();


    public void runJavascript(String filename) {
        JavascriptExecutor jse = null;
        String code = null;
        if (StringUtils.isNotBlank(filename)) {

            try {
                code = IOUtils.toString(this.getClass().getResourceAsStream(filename));
            } catch (IOException e) {
                logger.error("Unable to read the javascript contained in the file '", filename, "'");
            }
        }

        if (webDriver instanceof JavascriptExecutor) {

            jse = ((JavascriptExecutor) webDriver);
        } else {

            logger.error("the webdriver does not support running javascript code");
        }
        if (jse != null && code != null) {
            String form = (String) jse.executeScript(code);
        }
    }


}
