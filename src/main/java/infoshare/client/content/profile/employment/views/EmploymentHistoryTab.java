package infoshare.client.content.profile.employment.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.DomainState;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.employment.EmploymentMenu;
import hashwork.client.content.profile.employment.forms.EmploymentHistoryForm;
import hashwork.client.content.profile.employment.model.EmploymentHistoryModel;
import hashwork.client.content.profile.employment.table.EmploymentHistoryTable;
import hashwork.domain.people.PersonEmploymentHistory;
import hashwork.factories.people.PersonEmploymentHistoryFactory;

/**
 * Created by hashcode on 2015/12/07.
 */
public class EmploymentHistoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    final private EmploymentHistoryForm form;
    final private EmploymentHistoryTable table;

    public EmploymentHistoryTab(MainLayout main) {
        this.main = main;
        form = new EmploymentHistoryForm();
        table = new EmploymentHistoryTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        final String personId = GetUserCredentials.getUser().getId();
        if (property == table) {
            final PersonEmploymentHistory personEmploymentHistory = PeopleFacade.personEmployementHistoryService.findById(personId, table.getValue().toString());
            final EmploymentHistoryModel model = getModel(personEmploymentHistory);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personEmployementHistoryService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personEmployementHistoryService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonEmploymentHistory personAddress = PeopleFacade.personEmployementHistoryService.findById(personId, table.getValue().toString());
        final PersonEmploymentHistory updatedPersonAddress = new PersonEmploymentHistory
                .Builder().copy(personAddress)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personEmployementHistoryService.save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new EmploymentMenu(main, "EMPLOYMENT"));
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
        table.addValueChangeListener(this);
    }


    private PersonEmploymentHistory getNewEntity(FieldGroup binder) {
        final EmploymentHistoryModel model = ((BeanItem<EmploymentHistoryModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonEmploymentHistory personEmploymentHistory = PersonEmploymentHistoryFactory
                .getPersonEmployement(
                        personId,
                        model.getCompanyName(),
                        model.getCompanyAddress(),
                        model.getCompanyTelephone(),
                        model.getApplicatSupervisor(),
                        model.getContactSupervisor(),
                        model.getReasonsForLeaving(),
                        model.getStartDate(),
                        model.getEndDate(),
                        model.getStartingSalary(),
                        model.getEndingSalary(),
                        model.getCurrencyId(),
                        model.getJobResponsibility(),
                        model.getJobId()
                );
        return personEmploymentHistory;
    }


    private PersonEmploymentHistory getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final EmploymentHistoryModel model = ((BeanItem<EmploymentHistoryModel>) binder.getItemDataSource()).getBean();
        final PersonEmploymentHistory personEmploymentHistory = PeopleFacade.personEmployementHistoryService.findById(personId, table.getValue().toString());
        final PersonEmploymentHistory updatedPersonAddress = new PersonEmploymentHistory
                .Builder()
                .copy(personEmploymentHistory)
                .companyAddress(model.getCompanyAddress())
                .applicatSupervisor(model.getApplicatSupervisor())
                .companyName(model.getCompanyName())
                .companyTelephone(model.getCompanyTelephone())
                .contactSupervisor(model.getContactSupervisor())
                .currencyId(model.getCurrencyId())
                .endDate(model.getEndDate())
                .endingSalary(model.getEndingSalary())
                .jobId(model.getJobId())
                .jobResponsibility(model.getJobResponsibility())
                .reasonsForLeaving(model.getReasonsForLeaving())
                .startDate(model.getStartDate())
                .startingSalary(model.getStartingSalary())
                .build();
        return updatedPersonAddress;
    }

    private EmploymentHistoryModel getModel(PersonEmploymentHistory emph) {
        final EmploymentHistoryModel model = new EmploymentHistoryModel();
        model.setCompanyName(emph.getCompanyName());
        model.setCompanyAddress(emph.getCompanyAddress());
        model.setCompanyTelephone(emph.getCompanyTelephone());
        model.setApplicatSupervisor(emph.getApplicatSupervisor());
        model.setContactSupervisor(emph.getContactSupervisor());
        model.setReasonsForLeaving(emph.getReasonsForLeaving());
        model.setStartDate(emph.getStartDate());
        model.setEndDate(emph.getEndDate());
        model.setStartingSalary(emph.getStartingSalary());
        model.setEndingSalary(emph.getEndingSalary());
        model.setCurrencyId(emph.getCurrencyId());
        model.setJobResponsibility(emph.getJobResponsibility());
        model.setJobId(emph.getJobId());
        return model;
    }
}
