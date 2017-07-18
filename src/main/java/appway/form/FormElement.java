package appway.form;

/**
 * Created by crypt on 5/21/2016.
 */
public class FormElement {
    public enum TYPE {
        DROPDOWNLIST,
        CHECKBOXCHECKBOX,
        CHECKBOXLISTCHECKBOX,
        TEXTFIELD,
        DATEPICKER,
        TEXTAREA,
        /**/;
    }

    TYPE type;
    String name;
    String value;
    boolean checked;
}
