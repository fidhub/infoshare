package infoshare.client.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;

/**
 * Created by THULEBONA on 2015-06-22.
 */
public class Editor extends HorizontalLayout {

    public Editor(){
        setSizeFull();
        Component sidebar = buildsidebarForm();
        addComponent(sidebar);
        setComponentAlignment(sidebar, Alignment.MIDDLE_LEFT);


    }

    private Component buildsidebarForm() {
        final VerticalLayout EditorPanel = new VerticalLayout();
        EditorPanel.setSizeUndefined();
        EditorPanel.setSpacing(true);
        Responsive.makeResponsive(EditorPanel);
        EditorPanel.addStyleName("Editor-panel");

        EditorPanel.addComponent(new Button("Button 1"));
        EditorPanel.addComponent(new Button("Button 2"));
        EditorPanel.addComponent(new Button("Button 3"));
        EditorPanel.addComponent(new Button("Button 4"));
        EditorPanel.addComponent(new Button("Button 5"));
        EditorPanel.addComponent(new Button("Button 6"));
        Component t = buildContenier();
        EditorPanel.addComponent(t);
        return EditorPanel;
    }
    private Component buildContenier(){
        final VerticalLayout contentPanel = new VerticalLayout();
        contentPanel.setSizeUndefined();
        contentPanel.setSpacing(true);
        Responsive.makeResponsive(contentPanel);
        contentPanel.addStyleName("content-panel");


        final TextField phoneNumber = new TextField("Phone number");
        phoneNumber.setIcon(FontAwesome.PHONE);
        phoneNumber.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        contentPanel.addComponent(phoneNumber);
        final TextField username =new TextField("User Name");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        contentPanel.addComponent(username);

        return contentPanel;
    }
}
