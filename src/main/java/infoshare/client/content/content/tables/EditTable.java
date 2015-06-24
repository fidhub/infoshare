package infoshare.client.content.content.tables;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.models.EditModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{
   // private final MainLayout main;

    public EditTable(/*MainLayout mainApp*/){
        //this.main = mainApp;
        setSizeFull();
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Content Type",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Description",String.class,null);
        addContainerProperty("Date Created",String.class,null);
        addContainerProperty("",Component.class,null);
        List<EditModel> contents = new ArrayList<>();

       // for (EditModel content: contents){
            addItem(new Object[]{
                                "1","2","3","4","5","6",buttons()
                                },1);
       // }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    private Component buttons(){
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);

        final Button editBtn = new Button();
        editBtn.setIcon(FontAwesome.EDIT);
        editBtn.addStyleName(ValoTheme.BUTTON_TINY);
        editBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        editBtn.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button deleteBtn = new Button();
        deleteBtn.setIcon(FontAwesome.TRASH_O);
        deleteBtn.addStyleName(ValoTheme.BUTTON_TINY);
        deleteBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        deleteBtn.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        //deleteBtn.addClickListener();

        buttonsLayout.addComponent(editBtn);
        buttonsLayout.addComponent(deleteBtn);

        return buttonsLayout;

    }

}
