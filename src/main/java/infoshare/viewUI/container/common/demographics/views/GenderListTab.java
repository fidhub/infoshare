package infoshare.viewUI.container.common.demographics.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.util.DomainState;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.demographics.CommonDemographicsMenu;
import infoshare.viewUI.container.common.demographics.forms.GenderListForm;
import infoshare.viewUI.container.common.demographics.model.GenderListModel;
import infoshare.viewUI.container.common.demographics.table.GenderListTable;
import infoshare.domain.demographics.Gender;
import infoshare.factories.common.GenderListFactory;


/**
 * Created by hashcode on 2015/08/18.
 */
public class GenderListTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final GenderListForm form;
    private final GenderListTable table;

    public GenderListTab(MainLayout app) {
        main = app;
        form = new GenderListForm();
        table = new GenderListTable(main);
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
        if (property == table) {
            final Gender gender = DemographicsFacade.genderListService.findById(table.getValue().toString());
            final GenderListModel model = getModel(gender);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DemographicsFacade.genderListService.save(getNewEntity(binder));
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
            DemographicsFacade.genderListService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final Gender gender = DemographicsFacade.genderListService.findById(table.getValue().toString());
        final Gender updatedGender = new Gender
                .Builder()
                .copy(gender)
                .state(DomainState.RETIRED.name())
                .build();
        DemographicsFacade.genderListService.save(updatedGender);
        getHome();
    }



    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "GENDER"));
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
        //Buttons Behaviou
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
        //Register Table Listerners
        table.addValueChangeListener(this);
    }

    private Gender getNewEntity(FieldGroup binder) {
        final GenderListModel bean = ((BeanItem<GenderListModel>) binder.getItemDataSource()).getBean();
        final Gender gender = GenderListFactory.getGenderList(bean.getGender());
        return gender;
    }

    private Gender getUpdateEntity(FieldGroup binder) {
        final GenderListModel bean = ((BeanItem<GenderListModel>) binder.getItemDataSource()).getBean();
        final Gender gender = DemographicsFacade.genderListService.findById(table.getValue().toString());
        final Gender updatedGender = new Gender.Builder().copy(gender).name(bean.getGender()).build();
        return updatedGender;
    }

    private GenderListModel getModel(Gender gender) {
        final GenderListModel model = new GenderListModel();
        model.setGender(gender.getName());

        return model;
    }
}
