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
import infoshare.viewUI.container.common.demographics.forms.RaceListForm;
import infoshare.viewUI.container.common.demographics.model.RaceListModel;
import infoshare.viewUI.container.common.demographics.table.RaceListTable;
import infoshare.domain.demographics.Race;
import infoshare.factories.common.RaceListFactory;


/**
 * Created by hashcode on 2015/08/18.
 */
public class RaceListTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final RaceListForm form;
    private final RaceListTable table;

    public RaceListTab(MainLayout app) {
        main = app;
        form = new RaceListForm();
        table = new RaceListTable(main);
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
            final Race race = DemographicsFacade.raceListService.findById(table.getValue().toString());
            final RaceListModel model = getModel(race);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();

        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DemographicsFacade.raceListService.save(getNewEntity(binder));
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
            DemographicsFacade.raceListService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        final Race race = DemographicsFacade.raceListService.findById(table.getValue().toString());
        final Race updatedRace = new Race.Builder()
                .copy(race)
                .state(DomainState.RETIRED.name())
                .build();
        DemographicsFacade.raceListService.save(updatedRace);
        getHome();
    }



    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "RACE"));
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

    private Race getNewEntity(FieldGroup binder) {
        final RaceListModel bean = ((BeanItem<RaceListModel>) binder.getItemDataSource()).getBean();
        final Race race = RaceListFactory.getRaceList(bean.getName());
        return race;
    }

    private Race getUpdateEntity(FieldGroup binder) {
        final Race race = DemographicsFacade.raceListService.findById(table.getValue().toString());
        final RaceListModel bean = ((BeanItem<RaceListModel>) binder.getItemDataSource()).getBean();
        final Race updatedRace = new Race.Builder()
                .copy(race)
                .name(bean.getName())
                .build();
        return updatedRace;
    }

    private RaceListModel getModel(Race race) {
        final RaceListModel model = new RaceListModel();
        model.setName(race.getName());
        return model;
    }
}
