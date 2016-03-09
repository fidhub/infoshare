package infoshare.client.content.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.CategoryFacade;
import infoshare.client.content.MainLayout;
import infoshare.client.content.systemValues.SystemValues;
import infoshare.client.content.systemValues.forms.CategoryForm;
import infoshare.client.content.systemValues.models.CategoryModel;
import infoshare.client.content.systemValues.tables.CategoryTable;
import infoshare.domain.content.Category;
import infoshare.factories.content.CategoryFactory;
import infoshare.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    @Autowired
    private CategoryService categoryService = CategoryFacade.categoryService;

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
            final Category category = categoryService.findById(table.getValue().toString());
            final CategoryModel bean = getModel(category);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }

    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            categoryService.save(getNewEntity(binder));
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
            categoryService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        final Category category = getUpdateEntity(binder);
        categoryService.delete(category);
        getHome();
    }
    private Category getNewEntity(FieldGroup binder) {
        final CategoryModel bean = ((BeanItem<CategoryModel>) binder.getItemDataSource()).getBean();
        Map<String,String> categoryVals = new HashMap<>();
        categoryVals.put("name",bean.getName());
        categoryVals.put("description",bean.getDescription());
        final Category category = CategoryFactory.getCategory(categoryVals);
        return category;
    }
    private Category getUpdateEntity(FieldGroup binder) {
        final CategoryModel bean = ((BeanItem<CategoryModel>) binder.getItemDataSource()).getBean();
        final Category category = new Category.Builder()
                .name(bean.getName())
                .description(bean.getDescription())
                .id(table.getValue().toString())
                .build();
        return category;
    }
    private void getHome() {
       main.content.setSecondComponent(new SystemValues(main, "CATEGORY"));
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
    private CategoryModel getModel(Category category) {
        final CategoryModel model = new CategoryModel();
        model.setDescription(category.getDescription());
        model.setName(category.getName());
        return model;
    }
}
