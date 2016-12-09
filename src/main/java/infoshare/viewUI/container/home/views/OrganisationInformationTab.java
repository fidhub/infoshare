package infoshare.viewUI.container.home.views;



import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import infoshare.viewUI.container.MainLayout;
import org.apache.tools.ant.taskdefs.Javadoc;


import static java.lang.System.lineSeparator;



/**
 * Created by hashcode on 2015/12/04.
 */
public class OrganisationInformationTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

   final private MainLayout main;

    public OrganisationInformationTab(MainLayout main) {
        this.main = main;
        setVisible(true);
        setSizeFull();
        setMargin(true);
        setCaptionAsHtml(true);
        setCaption("WHAT IS INFOSHARE ABOUT AND HOW DOES IT WORK? "  + "<br/>" +
                    "" + "<br/>" +
                    "InfoShare is an online platform where Caregivers share Information on their experiences and" + "<br/>" +
                    "expertise to other Caregivers within the same organization and stays in communication with " + "<br/>" +
                    "the organization by means of receiving their daily duties, submitting daily reports, research " + "<br/>" +
                    "new medical way on how to carry out their tasks I a more efficient way and get in touch with " + "<br/>" +
                    "any emergency service when required to, all done on the Mobile Application of InfoShare. " + "<br/>" +
                    "" + "<br/>" +
                    "" + "<br/>" +
                    "InfoShare bridges the gap between community caregivers and the department of health " + "<br/>" +
                    "along with all the healthcare facilities within in the city.");
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
    }

}
