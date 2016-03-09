package infoshare.client.content.profile.contacts.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.LocationFacade;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.profile.contacts.model.PersonAddressModel;
import infoshare.client.content.profile.contacts.model.PersonContactsModel;
import infoshare.domain.location.ContactType;
import infoshare.domain.util.Status;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonContactForm extends FormLayout {
    private final PersonContactsModel bean;
    public final BeanItem<PersonContactsModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public PersonContactForm() {
        bean = new PersonContactsModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


//        private String addressTypeId;
//        private String contactValue;
//        private String status;
        TextField contactValue = UIComponent.getGridTextField("Contact  :", "contactValue", PersonAddressModel.class, binder);

        //ComboBox Fields
        final ComboBox addressTypeId = UIComboBox.getComboBox("Contact Type :", "addressTypeId", PersonAddressModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<ContactType> contactTypes = LocationFacade.contactListService.findAll();
                for (ContactType contactType : contactTypes) {
                    comboBox.addItem(contactType.getId());
                    comboBox.setItemCaption(contactType.getId(), contactType.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox status = UIComboBox.getComboBox("Contact Status :", "status", PersonAddressModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<Status> statuses = UtilFacade.statusService.findAll()
                        .stream()
                        .filter(stat -> stat.getName().equalsIgnoreCase("CONTACT"))
                        .collect(Collectors.toSet());
                for (Status status : statuses) {
                    comboBox.addItem(status.getId());
                    comboBox.setItemCaption(status.getId(), status.getValue());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(addressTypeId, 0, 0);
        grid.addComponent(contactValue, 1, 0);
        grid.addComponent(status, 2, 0);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
