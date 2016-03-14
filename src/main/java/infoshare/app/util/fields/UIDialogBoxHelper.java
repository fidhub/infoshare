package infoshare.app.util.fields;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/09/07.
 */
public class UIDialogBoxHelper<T>  extends FormLayout implements Serializable {

    private DialobBoxWindow dialobBoxWindow;


    //initializeDialogBox(String windowTitle, String WindowMessage)
    public DialobBoxWindow getOKDialogBox(String windowTitle, String WindowMessage) {
        dialobBoxWindow = new DialobBoxWindow(windowTitle);
        VerticalLayout subContent = new VerticalLayout();
        subContent.addComponent(new Label(WindowMessage));
        subContent.addComponent(dialobBoxWindow.getOKButtons());

        dialobBoxWindow.setContent(subContent);
        return dialobBoxWindow;
    }

    public DialobBoxWindow getOKCancelDialogBox(String windowTitle, String WindowMessage) {
        dialobBoxWindow = new DialobBoxWindow(windowTitle);
        VerticalLayout subContent = new VerticalLayout();
        subContent.addComponent(new Label(WindowMessage));
        subContent.addComponent(dialobBoxWindow.getOKCancelButtons());

        dialobBoxWindow.setContent(subContent);
        return dialobBoxWindow;
    }

    public DialobBoxWindow getYesNoDialogBox(String windowTitle, String WindowMessage) {
        dialobBoxWindow = new DialobBoxWindow(windowTitle);
        VerticalLayout subContent = new VerticalLayout();
        subContent.addComponent(new Label(WindowMessage));
        subContent.addComponent(dialobBoxWindow.getYesNoButtons());

        dialobBoxWindow.setContent(subContent);
        return dialobBoxWindow;
    }

    public DialobBoxWindow getYesNoCancelDialogBox(String windowTitle, String WindowMessage) {
        dialobBoxWindow = new DialobBoxWindow(windowTitle);
        VerticalLayout subContent = new VerticalLayout();
        subContent.addComponent(new Label(WindowMessage));
        subContent.addComponent(dialobBoxWindow.getYesNoCancelButtons());

        dialobBoxWindow.setContent(subContent);
        return dialobBoxWindow;
    }
}
