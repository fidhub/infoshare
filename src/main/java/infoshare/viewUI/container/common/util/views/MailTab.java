package infoshare.viewUI.container.common.util.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.util.CommonUtilMenu;
import infoshare.viewUI.container.common.util.forms.MailForm;
import infoshare.viewUI.container.common.util.models.MailModel;
import infoshare.viewUI.container.common.util.tables.MailTable;
import infoshare.domain.util.Mail;
import infoshare.factories.util.MailFactory;

import java.util.Date;



/**
 * Created by hashcode on 2015/11/29.
 */
public class MailTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final MailForm form;
    private final MailTable table;

    public MailTab(MainLayout app) {
        main = app;
        form = new MailForm();
        table = new MailTable();
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
            final Mail mail = UtilFacade.mailService.findById(OrganisationUtil.getCompanyCode(),table.getValue().toString());
            final MailModel model = getModel(mail);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            UtilFacade.mailService.save(getNewEntity(binder));
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
            UtilFacade.mailService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }




    private void getHome() {
        main.content.setSecondComponent(new CommonUtilMenu(main, "MAIL"));
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

    private void deleteForm(FieldGroup binder) {
        final Mail mail = UtilFacade.mailService.findById(table.getValue().toString());
        final Mail updateMail = new Mail
                .Builder().copy(mail)
                .state(DomainState.RETIRED.name())
                .build();
        UtilFacade.mailService.save(updateMail);
        getHome();
    }

    private Mail getNewEntity(FieldGroup binder) {
        final MailModel model = ((BeanItem<MailModel>) binder.getItemDataSource()).getBean();
        Mail mail = MailFactory.createMailConf(model.getKey(), model.getValue(), model.getHost(), model.getPort());
        return mail;
    }

    private Mail getUpdateEntity(FieldGroup binder) {
        final MailModel model = ((BeanItem<MailModel>) binder.getItemDataSource()).getBean();
        final Mail mail = UtilFacade.mailService.findById(OrganisationUtil.getCompanyCode(),table.getValue().toString());
        final Mail updateMail = new Mail
                .Builder().copy(mail)
                .date(new Date())
                .key(model.getKey())
                .value(model.getValue())
                .port(model.getPort())
                .host(model.getHost())
                .orgId(model.getOrgId())
                .build();
        return updateMail;
    }

    private MailModel getModel(Mail mail) {
        final MailModel model = new MailModel();
        try {
            model.setDate(mail.getDate());
            model.setHost(mail.getHost());
            model.setKey(mail.getKey());
            model.setPort(mail.getPort());
            model.setState(mail.getState());
            model.setValue(mail.getValue());
            model.setOrgId(mail.getOrgId());
        }catch (Exception e){e.printStackTrace();}
        return model;
    }
}
