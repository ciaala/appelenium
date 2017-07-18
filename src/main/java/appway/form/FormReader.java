package appway.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by crypt on 5/21/2016.
 */
public class FormReader {
    private static final Logger logger = LogManager.getLogger(FormReader.class);


    public static Form read(String filename) {
        Properties properties = new Properties();
        Form form = new Form();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = loader.getResourceAsStream(filename)) {
            properties.load(stream);
            properties.forEach((k, v) -> {
                try {
                    form.addElementInformation(k.toString(), v.toString());
                } catch (FormException e) {
                    logger.error(StringUtils.join("Unable to parse a form rule in file: '", filename, "'."), e);
                }
            });
            /*for (String key : .keys() ) {
                form.parse(key, properties())
            }*/
            return form;
        } catch (Exception e) {
            logger.error(StringUtils.join("Unable to parse file: '", filename, "'."), e);
        }

        return form;
    }
}
