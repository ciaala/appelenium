package appway.form;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * Created by crypt on 5/21/2016.
 */

public class Form {
    HashMap<String, FormElement> elements = new HashMap<>();


    void addElementInformation(String key, String value) throws FormException {
        if (StringUtils.isNotBlank(key)) {
            String tokens[] = key.split("\\.");

            if (tokens.length > 1) {
                String infoToken = tokens[tokens.length - 1];
                String elementName = StringUtils.join(tokens, ".", 0, tokens.length - 1);
                switch (infoToken) {
                    case "type": {
                        FormElement formElement = getFormElement(elementName);
                        formElement.type = FormElement.TYPE.valueOf(value.toUpperCase());
                        break;
                    }
                    case "checked": {
                        FormElement formElement = getFormElement(elementName);
                        formElement.checked = Boolean.valueOf(value);
                        break;
                    }
                    case "value": {
                        FormElement formElement = getFormElement(elementName);
                        formElement.value = value;
                        break;
                    }
                    default: {
                        throw new FormException(key, value);
                    }
                }

            }
        }
    }

    private FormElement getFormElement(String elementName) {
        FormElement formElement = elements.get(elementName);
        if (formElement == null) {
            formElement = new FormElement();
            formElement.name = elementName;
            elements.put(elementName, formElement);
        }
        return formElement;
    }
}
