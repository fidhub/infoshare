package infoshare.client.content.courseSetup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.courseSetup.CoursesMenu;
import infoshare.client.content.courseSetup.forms.LessonForm;
import infoshare.client.content.courseSetup.models.LessonModel;
import infoshare.client.content.courseSetup.tables.LessonTable;
import infoshare.services.courses.CourseService;
import infoshare.services.courses.Impl.Services;

import java.util.List;

/**
 * Created by codex on 2015/07/07.
 */

public class LessonView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final Window popUp;
    private final MainLayout main ;
    private final LessonForm form;
    private static LessonTable table = null;
    private Button addLesson = new Button("Add Lesson");
    private Button editLesson = new Button("Edit Lesson");
    private static ComboBox courseCmb = null;
    private CourseService courseService = new Services();

    public LessonView(MainLayout main) {
        this.main = main;
        this.form = new LessonForm();
        if(table == null){
            table = new LessonTable();
            addLesson.setVisible(false);
            editLesson.setVisible(false);
        }
        else if(courseCmb.getValue() != null)
              table.loadTable(courseCmb.getValue().toString());

        this.popUp = modelWindow();

        setSizeFull();
        setSpacing(true);
        addComponent(getComboBox());
        addComponent(getButtons());
        addComponent(table);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (source == addLesson){
                UI.getCurrent().addWindow(popUp);
                popUp.setModal(true);
                form.popUpSaveBtn.setVisible(true);
                form.popUpUpdateBtn.setVisible(false);

        }else if(source == editLesson){
            editButton();
        }else if(source== form.popUpCancelBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            getHome();
        }else if(source == form.popUpUpdateBtn){
            saveEditedForm(form.binder);
        }else if (source == form.popUpSaveBtn){
            saveForm(form.binder);
        }

    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == courseCmb){
            table.loadTable(courseCmb.getValue().toString());
            addLesson.setVisible(true);
            editLesson.setVisible(true);
        }
    }
    private void editButton(){
        try {
            final Course course = courseService.findById(courseCmb.getValue().toString());
            course.getLessons().stream().filter(lesson -> lesson.getId() == table.getValue().toString()).forEach(lesson -> {
                final LessonModel bean = getLessonModel(lesson);
                form.binder.setItemDataSource(new BeanItem<>(bean));
                UI.getCurrent().addWindow(popUp);
                popUp.setModal(true);
            });
        }catch (Exception e){
             Notification.show("Select the lesson you wanna edit", Notification.Type.HUMANIZED_MESSAGE);
        }finally {
            form.popUpUpdateBtn.setVisible(true);
            form.popUpSaveBtn.setVisible(false);
        }
    }
    private HorizontalLayout getButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        addLesson.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        addLesson.setIcon(FontAwesome.PLUS);
        editLesson.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        editLesson.setIcon(FontAwesome.EDIT);

        buttons.addComponent(addLesson);
        buttons.addComponent(editLesson);

        return buttons;
    }
    private Window modelWindow(){
        final Window popup = new Window();
        popup.setWidth(80.0f, Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
        popup.setContent(form);
        return popup;
    }
    public void addListeners(){
        form.popUpUpdateBtn.addClickListener(this);
        form.popUpSaveBtn.addClickListener(this);
        form.popUpCancelBtn.addClickListener(this);
        table.addValueChangeListener(this);
        addLesson.addClickListener(this);
        editLesson.addClickListener(this);
        courseCmb.addValueChangeListener(this);
    }
    private ComboBox getComboBox() {
        if (courseCmb == null)
            courseCmb = new ComboBox();
        for (Course course : courseService.findAll()) {
            if (course.getId() != null) {
                courseCmb.addItem(course.getId());
                courseCmb.setItemCaption(course.getId(),
                        course.getCourseLevel() + " " +
                        course.getCourseDescription());
                courseCmb.setNullSelectionAllowed(false);
                courseCmb.setImmediate(true);
            }
        }
        courseCmb.setWidth(300.0f, Unit.PIXELS);
        courseCmb.setInputPrompt("Select Course.....");
        courseCmb.addStyleName(ValoTheme.COMBOBOX_SMALL);
        return courseCmb;
    }
    private void getHome(){
        main.content.setSecondComponent(new CoursesMenu(main,"LANDING"));
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            courseService.update(getUpdateEntity(binder));
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.HUMANIZED_MESSAGE);

        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }catch(Exception dp){
            Notification.show("Course is already exist!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            courseService.save(getNewEntity(binder));
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private Course getUpdateEntity(FieldGroup binder) {

        final Course courseBean = courseService.findById(courseCmb.getValue().toString());
        final LessonModel lessonBean = ((BeanItem<LessonModel>) binder.getItemDataSource()).getBean();
        List<Lesson> lessons = courseBean.getLessons();

        final Lesson lesson = new Lesson.Builder(lessonBean.getLesson())
                            .description(lessonBean.getDescription())
                            .content(lessonBean.getContent())
                            .id(table.getValue().toString())
                            .build();

        for(int i=0; i<lessons.size(); i++)
            if(lessons.get(i).getId().equals(lesson.getId()))
                lessons.remove(i);

        lessons.add(lesson);
        final Course course = new Course.Builder(courseBean.getCourseLevel())
                             .description(courseBean.getCourseDescription())
                             .lessons(lessons).id(courseBean.getId()).build();
        return course;
    }
    private Course getNewEntity(FieldGroup binder) {

        final Course courseBean = courseService.findById(courseCmb.getValue().toString());
        final LessonModel lessonBean = ((BeanItem<LessonModel>) binder.getItemDataSource()).getBean();
        List<Lesson> lessons = courseBean.getLessons();

        int size = lessons.size();
        final Lesson lesson = new Lesson.Builder(lessonBean.getLesson())
                             .description(lessonBean.getDescription())
                             .content(lessonBean.getContent())
                             .id((size+1)+"").build();
        lessons.add(lesson);
        final Course course = new Course.Builder(courseBean.getCourseLevel())
                            .description(courseBean.getCourseDescription())
                            .lessons(lessons).id(courseBean.getId()).build();
        return course;
    }
    public LessonModel getLessonModel(Lesson lesson) {
        LessonModel  model = new LessonModel();
        model.setLesson(lesson.getLesson());
        model.setDescription(lesson.getDescription());
        model.setContent(lesson.getContent());
        model.setId(lesson.getId());
        return model;
    }
}
