package infoshare.client.content.profile.employment.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.JobFacade;
import hashwork.app.facade.UtilFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.profile.employment.model.EmploymentHistoryModel;
import hashwork.domain.payroll.ui.job.JobClassification;
import hashwork.domain.ui.util.Currency;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/21.
 */
public class EmploymentHistoryForm extends FormLayout {
    private final EmploymentHistoryModel bean;
    public final BeanItem<EmploymentHistoryModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public EmploymentHistoryForm() {
        bean = new EmploymentHistoryModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();

        TextField companyName = UIComponent.getGridTextField("Company Name :", "companyName", EmploymentHistoryModel.class, binder);
        TextField applicatSupervisor = UIComponent.getGridTextField("Supervisor Name :", "applicatSupervisor", EmploymentHistoryModel.class, binder);
        TextField companyTelephone = UIComponent.getGridTextField("Company Telephone :", "companyTelephone", EmploymentHistoryModel.class, binder);

        DateField startDate = UIComponent.getGridDateField("Start Date:", "startDate", EmploymentHistoryModel.class, binder);
        DateField endDate = UIComponent.getGridDateField("End Date :", "endDate", EmploymentHistoryModel.class, binder);

        CheckBox contactSupervisor = UIComponent.getGridCheckBox("Contact Supervisor :", "contactSupervisor", EmploymentHistoryModel.class, binder);

        TextArea companyAddress = UIComponent.getGridTextArea("Company Address :", "companyAddress", EmploymentHistoryModel.class, binder);
        TextArea reasonsForLeaving = UIComponent.getGridTextArea("Reason For Leaving :", "reasonsForLeaving", EmploymentHistoryModel.class, binder);
        TextArea jobResponsibility = UIComponent.getGridTextArea("Job Responsibility :", "jobResponsibility", EmploymentHistoryModel.class, binder);


        TextField startingSalary = UIComponent.getGridBigDecimalTextField("Starting Salary :", "startingSalary", EmploymentHistoryModel.class, binder);

        TextField endingSalary = UIComponent.getGridBigDecimalTextField("Ending Salary :", "endingSalary", EmploymentHistoryModel.class, binder);



        //ComboBox Fields
        final ComboBox jobId = UIComboBox.getComboBox("Job  :", "jobId", EmploymentHistoryModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<JobClassification> josbs = JobFacade.jobClassificationService.findAll();
                for (JobClassification job : josbs) {
                    comboBox.addItem(job.getId());
                    comboBox.setItemCaption(job.getId(), job.getCurrentTitle());
                }
            }
        });

        //ComboBox Fields
        final ComboBox currencyId = UIComboBox.getComboBox("Currency  :", "currencyId", EmploymentHistoryModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<Currency> currencies = UtilFacade.currencyServices.findAll();
                for (Currency currency : currencies) {
                    comboBox.addItem(currency.getId());
                    comboBox.setItemCaption(currency.getId(), currency.getName());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();




        // First ROW
        grid.addComponent(jobId, 0, 0);
        grid.addComponent(companyName, 1, 0);
        grid.addComponent(applicatSupervisor, 2, 0);

        grid.addComponent(startDate, 0, 1);
        grid.addComponent(endDate, 1, 1);
        grid.addComponent(companyTelephone, 2, 1);

        grid.addComponent(currencyId, 0, 2);
        grid.addComponent(startingSalary, 1, 2);
        grid.addComponent(endingSalary, 2, 2);

        //Second ROW
        grid.addComponent(companyAddress, 0, 3, 0, 4);
        grid.addComponent(jobResponsibility, 1, 3, 1, 4);
        grid.addComponent(reasonsForLeaving, 2, 3, 2, 4);
        grid.addComponent(contactSupervisor, 0, 5);


        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 7, 2, 7);

        addComponent(grid);
    }
}
