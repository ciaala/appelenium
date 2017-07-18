package appway.form;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by crypt on 5/21/2016.
 */
public class FormException extends Exception {
    public FormException(String key, String value) {
        super(StringUtils.join("Unable to use the form information (key=", key, "value=", value, ")"));
    }
}
