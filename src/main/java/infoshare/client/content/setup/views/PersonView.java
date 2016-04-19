package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.facade.RoleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.PersonForm;
import infoshare.client.content.setup.models.PersonModel;
import infoshare.client.content.setup.tables.PersonTable;
import infoshare.domain.person.Person;
import infoshare.factories.person.PersonFactory;
import infoshare.services.people.PersonRoleService;
import infoshare.services.people.PersonService;
import infoshare.services.roles.RoleService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hashcode on 2015/06/23.
 */
public class PersonView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final PersonForm personForm;
    private final PersonTable table;
    private PersonService personService = PeopleFacade.personService;
    private RoleService roleService = RoleFacade.roleService;
    private PersonRoleService rolesListService = PeopleFacade.personRoleService;
    public PersonView(MainLayout app) {
        main = app;
        personForm = new PersonForm();
        table = new PersonTable(main);
        setSizeFull();
        addComponent(personForm);
        addComponent(table);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent event) {

        final Button source = event.getButton();
        if (source == personForm.save) {
            saveForm(personForm.binder);
        } else if (source == personForm.edit) {
            setEditFormProperties();
        } else if (source == personForm.cancel) {
            getHome();
        } else if (source == personForm.update) {
            saveEditedForm(personForm.binder);
        } else if (source == personForm.delete) {
            deleteForm(personForm.binder);
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            try {
                final Person person = personService.getPersonById(OrganisationUtil.getCompanyCode(),table.getValue().toString());
                final PersonModel bean = getModel(person);
                personForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){
            }
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            personService.save(getPersonNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        } catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            personService.update(getPersonUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        personService.delete(getPersonUpdateEntity(binder));
        getHome();
    }
    private Person getPersonNewEntity(FieldGroup binder) {
        final PersonModel bean = ((BeanItem<PersonModel>) binder.getItemDataSource()).getBean();
        Map<String,Boolean> boolVals = new HashMap<>();
        boolVals.put("enabled",bean.getEnabled());
        boolVals.put("accountNonExpired",bean.getAccountNonExpired());
        boolVals.put("accountNonLocked",bean.getAccountNonLocked());
        boolVals.put("credentialsNonExpired",bean.getCredentialsNonExpired());
        Map<String,String> stringVals = new HashMap<>();
        stringVals.put("firstName",bean.getFirstName());
        stringVals.put("middleName",bean.getMiddleName());
        stringVals.put("lastName",bean.getMiddleName());
        stringVals.put("authvalue",bean.getAuthvalue());
        stringVals.put("emailAddress",bean.getEmailAddress());
        stringVals.put("org",bean.getOrg());
        final Person person = PersonFactory.getPerson(stringVals, boolVals);
        return person;
    }
    private Person getPersonUpdateEntity(FieldGroup binder) {
        final PersonModel bean = ((BeanItem<PersonModel>) binder.getItemDataSource()).getBean();
        Map<String,Boolean> boolVals = new HashMap<>();
        boolVals.put("enabled",bean.getEnabled());
        boolVals.put("accountNonExpired",bean.getAccountNonExpired());
        boolVals.put("accountNonLocked",bean.getAccountNonLocked());
        boolVals.put("credentialsNonExpired",bean.getCredentialsNonExpired());
        Map<String,String> stringVals = new HashMap<>();
        stringVals.put("firstName",bean.getFirstName());
        stringVals.put("middleName",bean.getMiddleName());
        stringVals.put("lastName",bean.getMiddleName());
        stringVals.put("authvalue",bean.getAuthvalue());
        stringVals.put("emailAddress",bean.getEmailAddress());
        stringVals.put("org",bean.getOrg());
        final Person person = PersonFactory.getPerson(stringVals, boolVals);
        return person;

    }
    public PersonModel getModel(Person user) {

        PersonModel model = new PersonModel();
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());

        return model;
    }
    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
    }
    private void setEditFormProperties() {
        personForm.binder.setReadOnly(false);
        personForm.save.setVisible(false);
        personForm.edit.setVisible(false);
        personForm.cancel.setVisible(true);
        personForm.delete.setVisible(false);
        personForm.update.setVisible(true);
    }
    private void setReadFormProperties() {
        personForm.binder.setReadOnly(true);
        //Buttons Behaviour
        personForm.save.setVisible(false);
        personForm.edit.setVisible(true);
        personForm.cancel.setVisible(true);
        personForm.delete.setVisible(true);
        personForm.update.setVisible(false);
    }
    private void addListeners() {
        //Register Button Listeners
        personForm.save.addClickListener(this);
        personForm.edit.addClickListener(this);
        personForm.cancel.addClickListener(this);
        personForm.update.addClickListener(this);
        table.addValueChangeListener(this);
        personForm.rolesList.addValueChangeListener(this);
    }

}
