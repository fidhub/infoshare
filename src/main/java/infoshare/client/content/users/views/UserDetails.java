package infoshare.client.content.users.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.UserManagementMenu;
import infoshare.client.content.users.forms.UserForm;
import infoshare.client.content.users.model.UserModel;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;

import java.util.Set;

/**
 * Created by THULEH on 2016/04/01.
 */
public class UserDetails extends VerticalLayout implements Button.ClickListener {

    private final MainLayout main;
    private final GridLayout grid;
    private final UserForm form;
    private final String tab;
    private String selectedUserId;
    private String selectedUserOrgId;
    private final Person person;
    public UserDetails(MainLayout main,Person per, String tab) {
        this.main = main;
        form = new UserForm(main);
        person = per;
        selectedUserId = person.getId();
        selectedUserOrgId = person.getOrg();
        this.tab = tab;
        final UserModel model = getModel(person);
        form.binder.setItemDataSource(new BeanItem<>(model));
        Label heading = new Label("<center><font color = \"40E0D0\"><h1>Details for " + person.getFirstName() +" "+person.getLastName()+"</h1></center>", ContentMode.HTML);
        heading.setStyleName(ValoTheme.LABEL_COLORED);
        heading.setSizeFull();
        heading.setSizeFull();

        grid = new GridLayout(4, 10);
        grid.setSizeFull();
        // First ROW
        grid.addComponent(heading, 0, 0, 3, 0);
        grid.addComponent(form, 0, 1, 3, 1);
        setReadFormProperties();
        addComponent(grid);
        addListeners();
    }



    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
       if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }
    private void deleteForm(FieldGroup binder) {
        Person updatedPerson = new Person
                .Builder()
                .copy(person)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personService.update(updatedPerson);
        getHome();

    }

    private void saveEditedForm(FieldGroup binder) {
        Person updatedPerson = getUpdateEntity(binder);
        PeopleFacade.personService.update(updatedPerson);
        try {
            Set<PersonRole> personRole = PeopleFacade.personRoleService.findPersonRoles(updatedPerson.getId());
            final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
            if(!personRole.contains(bean.getRole())){
                PersonRole role = new PersonRole.Builder()
                        .roleId(bean.getRole())
                        .personId(selectedUserId)
                        .state(DomainState.ACTIVE.name())
                        .build();
                PeopleFacade.personRoleService.save(role);
            }
        }catch (Exception e){}
        getHome();
    }

    private UserModel getModel(Person user) {
        UserModel model = new UserModel();
        model.setEmailAddress(user.getEmailAddress());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setMiddleName(user.getMiddleName());
        model.setOrg(user.getOrg());
        model.setRole(PeopleFacade.personRoleService.findPersonRoles(person.getId()).iterator().next().getRoleId());
        return model;
    }

    private Person getUpdateEntity(FieldGroup binder) {
        try {
            binder.commit();
            final Person company = PeopleFacade.personService.getPersonById(selectedUserOrgId,selectedUserId);
            final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
            final Person updatedPerson = new Person.Builder()
                    .copy(company)
                    .firstName(bean.getFirstName())
                    .lastName(bean.getLastName())
                    .emailAddress(bean.getEmailAddress())
                    .middleName(bean.getMiddleName())
                    .build();
            return updatedPerson;
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void getHome() {
        main.content.setSecondComponent(new UserManagementMenu(main, tab));
    }
    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }
    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener(this);
        form.edit.addClickListener(this);
        form.cancel.addClickListener(this);
        form.update.addClickListener(this);
        form.delete.addClickListener(this);

        //Register Table Listeners
    }

}
