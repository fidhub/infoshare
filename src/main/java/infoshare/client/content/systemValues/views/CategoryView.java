package infoshare.client.content.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.systemValues.forms.CategoryForm;
import infoshare.client.content.systemValues.models.CategoryModel;
import infoshare.client.content.systemValues.tables.CategoryTable;
import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    private final MainLayout main;
    private final CategoryForm form;
    private final CategoryTable table;

    public CategoryView(MainLayout app){
        main = app;
        form = new CategoryForm();
        table = new CategoryTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        final Button source = clickEvent.getButton();
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
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table){
            final Category category = categoryService.find(table.getValue().toString());
            final CategoryModel bean = getModel(category);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }

    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            categoryService.save(getEntity(binder));
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
//            RoleFacade.getRoleService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final Category category = getEntity(binder);
        categoryService.remove(category);
        getHome();
    }

    private Category getEntity(FieldGroup binder) {
        final CategoryModel bean = ((BeanItem<CategoryModel>) binder.getItemDataSource()).getBean();
        final Category ctgry = new Category.Builder(bean.getName())
                .description(bean.getDescription())
                .id(bean.getId())
                .build();
        return ctgry;
    }

    private void getHome() {
//        main.Content.setSecondComponent(new SetupMenu((main, "ROLES"));
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
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private CategoryModel getModel(Category val) {
        final CategoryModel model = new CategoryModel();
        final Category ctgry = categoryService.find(val.getId());
        model.setDescription(ctgry.getDescription());
        model.setId(ctgry.getId());
        model.setName(ctgry.getName());
        return model;
    }
}
