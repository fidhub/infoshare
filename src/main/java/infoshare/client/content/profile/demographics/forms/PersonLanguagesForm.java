package infoshare.client.content.profile.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.profile.demographics.model.PersonLanguageModel;
import infoshare.domain.demographics.Language;
import infoshare.domain.demographics.LanguageProficiency;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonLanguagesForm extends FormLayout {
    private final PersonLanguageModel bean;
    public final BeanItem<PersonLanguageModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public PersonLanguagesForm() {
        bean = new PersonLanguageModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


//        private String languageId;
//        private String reading;
//        private String writing;
//        private String speaking;


        //ComboBox Fields
        final ComboBox languageId = UIComboBox.getComboBox("Language :", "languageId", PersonLanguageModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<Language> languages = DemographicsFacade.getLanguageServiceInstance().findAll();
                for (Language language : languages) {
                    comboBox.addItem(language.getId());
                    comboBox.setItemCaption(language.getId(), language.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox reading = UIComboBox.getComboBox("Reading :", "reading", PersonLanguageModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<LanguageProficiency> languageProficiencies = DemographicsFacade.getLanguageProficiencyServiceInstance().findAll();
                for (LanguageProficiency languageProficiency : languageProficiencies) {
                    comboBox.addItem(languageProficiency.getId());
                    comboBox.setItemCaption(languageProficiency.getId(), languageProficiency.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox writing = UIComboBox.getComboBox("Writing :", "writing", PersonLanguageModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<LanguageProficiency> languageProficiencies = DemographicsFacade.getLanguageProficiencyServiceInstance().findAll();
                for (LanguageProficiency languageProficiency : languageProficiencies) {
                    comboBox.addItem(languageProficiency.getId());
                    comboBox.setItemCaption(languageProficiency.getId(), languageProficiency.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox speaking = UIComboBox.getComboBox("Speaking :", "speaking", PersonLanguageModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<LanguageProficiency> languageProficiencies = DemographicsFacade.getLanguageProficiencyServiceInstance().findAll();
                for (LanguageProficiency languageProficiency : languageProficiencies) {
                    comboBox.addItem(languageProficiency.getId());
                    comboBox.setItemCaption(languageProficiency.getId(), languageProficiency.getName());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(languageId, 0, 0);
        grid.addComponent(reading, 1, 0);
        grid.addComponent(writing, 2, 0);

        grid.addComponent(speaking, 0, 1);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
