package infoshare.client.content.courseSetup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.courseSetup.CoursesMenu;
import infoshare.client.content.courseSetup.forms.CoursesForm;
import infoshare.client.content.courseSetup.models.CourseModel;
import infoshare.client.content.courseSetup.tables.CoursesTable;
import infoshare.domain.Course;
import infoshare.services.courses.CourseService;
import infoshare.services.courses.Impl.CourseServiceImpl;

/**
 * Created by codex on 2015/07/07.
 */
public class CourseView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final CoursesForm form;
    private final CoursesTable table;
    private CourseService courseService = new CourseServiceImpl();

    public CourseView(MainLayout main) {
        this.main = main;
        form = new CoursesForm();
        table = new CoursesTable();
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
            try {
                final Course course = courseService.find(table.getValue().toString());
                final CourseModel bean = getModel(course);
                form.binder.setItemDataSource(new BeanItem<>(bean));
            }catch (Exception e){
                Notification.show(e.toString(), Notification.Type.WARNING_MESSAGE);
            }
            setReadFormProperties();
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            courseService.save(getNewEntity(binder));
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
            courseService.merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        courseService.remove(getUpdateEntity(binder));
        getHome();
    }
    private Course getNewEntity(FieldGroup binder) {
        final CourseModel bean = ((BeanItem<CourseModel>) binder.getItemDataSource()).getBean();
        final Course course = new Course.Builder(bean.getCourseLevel())
                .description(bean.getCourseDescription())
                .lessons(bean.getLessons())
                .build();
        return course;
    }
    private Course getUpdateEntity(FieldGroup binder) {
        final CourseModel bean = ((BeanItem<CourseModel>) binder.getItemDataSource()).getBean();
        final Course course = new Course.Builder(bean.getCourseLevel())
                .description(bean.getCourseDescription())
                .lessons(bean.getLessons())
                .id(table.getValue().toString())
                .build();
        return course;
    }
    private void getHome() {
        main.content.setSecondComponent(new CoursesMenu(main, "Course"));
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
    private CourseModel getModel(Course course) {
        final CourseModel model = new CourseModel();
        model.setCourseLevel(course.getCourseLevel());
        model.setCourseDescription(course.getCourseDescription());
        model.setLessons(course.getLessons());
        return model;
    }
}
